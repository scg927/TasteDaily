# TasteDaily iOS GitHub Actions 云端构建指南

> 版本：1.0 ｜ 日期：2026-07-07
> 目标：在 GitHub 云端 macOS Runner 上构建 iOS App（Framework / IPA）

---

## 一、方案总览

```
你的 Windows 电脑                    GitHub 云端 macOS Runner
     │                                        │
     │  git push (tag 或手动触发)              │
     │ ──────────────────────────────────────> │
     │                                        │
     │                              ┌──────────┤
     │                              │ 1. JDK 17 │
     │                              │ 2. Xcode  │
     │                              │ 3. Gradle │
     │                              │ 4. 编译   │
     │                              │ 5. 签名   │
     │                              │ 6. 导出   │
     │                              └──────────┤
     │                                        │
     │  <── 下载 Artifact (.ipa / .framework) ─│
     │                                        │
```

**核心原理**：GitHub Actions 提供免费的 macOS 云端机器（macos-15），预装了 Xcode 16.4、JDK、Gradle。你只需推送代码，云端自动编译 iOS 产物，构建完成后从 Actions 页面下载。

---

## 二、前置条件

### 2.1 仓库要求

| 条件 | 状态 | 说明 |
|------|------|------|
| 仓库公开（public） | ✅ | macOS runner 完全免费，无分钟数限制 |
| 仓库私有（private） | ⚠️ | macOS 分钟数按 10× 计费，Free 套餐 2000 分钟≈200 分钟实际使用 |

> **强烈建议将仓库设为 public**，这样 macOS runner 免费无限制。

### 2.2 Apple 开发者账号（按需选择）

| 场景 | 需要的账号 | 费用 | 产物有效期 |
|------|-----------|------|-----------|
| 仅编译 Framework（验证代码可编译） | 不需要 | 免费 | 永久 |
| 编译 IPA 安装到自己手机测试 | Apple Developer Program | $99/年 | 1 年 |
| 上架 App Store | Apple Developer Program | $99/年 | 永久 |
| 免费 Apple Account 临时测试 | Apple ID（免费） | 免费 | **7 天过期** |

---

## 三、已创建的 Workflow 文件

项目已包含两个 GitHub Actions workflow：

### 3.1 `.github/workflows/build-ios.yml` — Framework 构建

**触发条件**：推送 `v*` 标签 或 手动触发
**功能**：编译 Kotlin Multiplatform 的 iOS Framework（无需 Apple 账号）

```
产出：TasteDailyCore.framework（iOS arm64 release）
用途：验证 iOS 代码可编译，或供 Xcode 工程嵌入
```

### 3.2 `.github/workflows/build-ios-ipa.yml` — IPA 构建

**触发条件**：推送 `v*` 标签 或 手动触发
**功能**：编译 Framework + Xcode 归档 + 导出 IPA（需配置签名 Secrets）

---

## 四、操作步骤

### 步骤 1：触发 Framework 构建（无需签名，立即可用）

**方式 A：推送版本标签自动触发**
```bash
git tag -a v3.1.0 -m "测试 iOS 构建"
git push origin v3.1.0
```

**方式 B：GitHub 网页手动触发**
1. 打开 https://github.com/scg927/TasteDaily/actions
2. 左侧选择 "Build iOS" workflow
3. 点击 "Run workflow" → 选择 main 分支 → Run

**查看结果**：
- Actions 页面可实时查看编译日志
- 构建成功后，在构建详情页的 "Artifacts" 区域下载 `TasteDailyCore-ios-framework`

---

### 步骤 2：配置 Apple 代码签名 Secrets（构建 IPA 前必做）

> 如果只编译 Framework 验证代码，可跳过此步。

#### 2.1 获取签名证书 (.p12)

在 **macOS 电脑**上操作（可用朋友的 Mac 或云 Mac）：

1. 打开"钥匙串访问"（Keychain Access）
2. 选择"登录"钥匙串 → "证书"分类
3. 找到你的 Apple Development / Distribution 证书
4. 右键 → "导出..." → 保存为 `.p12` 格式，设置密码
5. 将 .p12 文件转为 base64：
```bash
base64 -i certificate.p12 -o certificate_base64.txt
```

#### 2.2 获取 Provisioning Profile

1. 登录 https://developer.apple.com/account/resources
2. Profiles → 创建一个 App ID（`com.tastedaily.app`）
3. 创建 Provisioning Profile（Ad Hoc 或 Development）
4. 下载 `.mobileprovision` 文件
5. 转为 base64：
```bash
base64 -i TasteDaily.mobileprovision -o profile_base64.txt
```

#### 2.3 在 GitHub 仓库配置 Secrets

1. 打开 https://github.com/scg927/TasteDaily/settings/secrets/actions
2. 点击 "New repository secret"，逐个添加：

| Secret 名称 | 值 | 说明 |
|-------------|------|------|
| `BUILD_CERTIFICATE_BASE64` | certificate_base64.txt 的内容 | .p12 证书的 base64 |
| `P12_PASSWORD` | 你的 .p12 密码 | 证书导出时设的密码 |
| `PROVISIONING_PROFILE_BASE64` | profile_base64.txt 的内容 | 描述文件的 base64 |
| `APPLE_TEAM_ID` | 你的 Team ID | Apple Developer 账号里的 Team ID |

---

### 步骤 3：创建 Xcode 工程（需 macOS 一次性操作）

> 当前 `iosApp/` 目录只有 Swift 入口文件，还没有完整的 `.xcodeproj`。
> 需在 macOS 上用 Xcode 创建一次，之后提交到 GitHub 即可。

#### 3.1 在 macOS 上创建 Xcode 工程

1. 用 Mac 打开项目：
```bash
git clone https://github.com/scg927/TasteDaily.git
cd TasteDaily
```

2. 先编译 Framework：
```bash
./gradlew :core:linkDebugFrameworkIosArm64
```

3. 打开 Xcode → File → New → Project
   - Product Name: `TasteDaily`
   - Bundle Identifier: `com.tastedaily.app`
   - 保存到 `iosApp/` 目录

4. 在 Xcode 中配置：
   - General → Frameworks, Libraries, and Embedded Content → 添加 `TasteDailyCore.framework`
   - Framework 路径设为 `$(SRCROOT)/../core/build/bin/iosArm64/releaseFramework`
   - Build Phases → 添加 Run Script Phase：
     ```bash
     cd "$SRCROOT/.."
     ./gradlew :core:linkReleaseFrameworkIosArm64 -PKOTLIN_FRAMEWORK_BUILD_TYPE=Release
     ```
   - 确保 Run Script 在 "Compile Sources" 之前

5. 提交 Xcode 工程到 GitHub：
```bash
git add iosApp/
git commit -m "feat: 添加 iOS Xcode 工程"
git push
```

#### 3.2 验证云端构建

推送后，在 GitHub Actions 页面触发 "Build iOS IPA" workflow。
构建成功后下载 `TasteDaily-ios` artifact，内含 `.ipa` 文件。

---

### 步骤 4：安装 IPA 到手机

#### 方式 A：通过 TestFlight（需 App Store Connect）
```bash
# 用 Xcode 或 altool 上传到 App Store Connect
xcrun altool --upload-app -f TasteDaily.ipa \
  -u "apple@email.com" -p "app-specific-password"
```
然后在 TestFlight App 中安装。

#### 方式 B：通过 Xcode 安装
1. iPhone 连接 Mac
2. Xcode → Window → Devices and Simulators
3. 选择你的设备 → 将 .ipa 拖入窗口

#### 方式 C：通过 Apple Configurator
1. iPhone 连接 Mac
2. 打开 Apple Configurator 2
3. 拖入 .ipa 文件安装

---

## 五、Workflow 详解

### build-ios.yml 核心流程

```yaml
runs-on: macos-15          # macOS Sequoia + Xcode 16.4（免费）
steps:
  - checkout                # 拉取代码
  - setup-java@v4 (JDK 17)  # 安装 JDK
  - setup-gradle@v4         # 缓存 Gradle
  - xcode-select            # 选择 Xcode 版本
  - gradlew linkRelease...  # 编译 iOS Framework
  - upload-artifact         # 上传 .framework 产物
```

### build-ios-ipa.yml 核心流程

```yaml
runs-on: macos-15
steps:
  - checkout
  - setup-java (JDK 17)
  - import-codesign-certs   # 导入签名证书（需 Secrets）
  - install provisioning    # 安装描述文件（需 Secrets）
  - gradlew linkRelease...  # 编译 Framework
  - xcodebuild archive      # Xcode 归档
  - xcodebuild export       # 导出 IPA
  - upload-artifact         # 上传 .ipa 产物
```

---

## 六、费用估算

| 场景 | Runner | 耗时 | 费用 |
|------|--------|------|------|
| 公开仓库 | macos-15 | ~10 分钟 | **免费** |
| 私有仓库（Pro 套餐） | macos-15 | ~10 分钟 | 消耗 100 分钟配额（10× 乘数） |
| 私有仓库超配额 | macos-15 | ~10 分钟 | $0.08 × 10 = $0.8 |

> **建议**：保持仓库 public，macOS runner 完全免费。

---

## 七、常见问题

### Q1：没有 Mac，怎么创建 Xcode 工程？

**选项 A**：使用 GitHub Codespaces（云端 VS Code + macOS）
**选项 B**：借朋友的 Mac 操作一次，Xcode 工程创建后提交到 GitHub，之后云端自动构建
**选项 C**：使用 macincloud.com 等云端 Mac 租用服务（约 $1/小时）

### Q2：构建失败怎么办？

1. 在 Actions 页面点击失败的构建，查看日志
2. 常见错误：
   - `No such module 'TasteDailyCore'` → Framework 路径配置错误
   - `Code signing failed` → 证书/描述文件未正确配置
   - `Unsupported class version` → JDK 版本不匹配，确保用 JDK 17

### Q3：能否不签名只编译？

可以。使用 `build-ios.yml`（非 IPA 版本），只编译 Framework，不需要 Apple 账号。

### Q4：iOS 模拟器版本？

macos-15 runner 包含 iOS 18.0-18.5 模拟器和 iOS 26.0-26.2 模拟器。

---

## 八、完整操作流程总结

```
1. 确保仓库为 public（免费 macOS runner）
2. 推送代码或打标签触发 build-ios.yml
   → 云端自动编译 Framework ✅（无需 Mac，无需 Apple 账号）
3. [可选] 在 Mac 上创建 Xcode 工程，提交到 GitHub
4. [可选] 配置签名 Secrets（证书 + 描述文件）
5. [可选] 触发 build-ios-ipa.yml
   → 云端自动编译 + 签名 + 导出 IPA ✅（需 Apple Developer $99/年）
6. 从 Actions 页面下载 .ipa，安装到手机
```
