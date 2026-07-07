# TasteDaily 跨平台编译指南

> 版本：v3.0.0 ｜ 日期：2026-07-07
> 支持 Android APK / iOS IPA / HarmonyOS HAP 三端编译

---

## 一、架构总览

```
TasteDaily/
├── core/                    # KMP 共享模块（跨平台业务逻辑）
│   ├── build.gradle.kts     # Kotlin Multiplatform 配置
│   └── src/
│       ├── commonMain/      # 共享代码（模型、选菜算法、菜谱数据）
│       ├── androidMain/     # Android 平台特定代码（当前为空）
│       └── iosMain/         # iOS 平台特定代码（当前为空）
├── app/                     # Android 应用模块
│   ├── build.gradle.kts     # Android + Compose 配置
│   └── src/main/            # Android UI（日历、菜谱、详情、视频）
├── iosApp/                  # iOS 应用入口（需 macOS + Xcode 编译）
│   └── TasteDailyApp.swift  # SwiftUI 入口，桥接 Compose Multiplatform
├── harmonyApp/              # 鸿蒙应用骨架（需 DevEco Studio 编译）
│   └── entry/src/main/ets/  # ArkTS + ArkUI 代码
│       ├── pages/Index.ets  # 日历主页
│       ├── data/DishData.ets # 菜谱数据与选菜算法（与 core 逻辑一致）
│       └── utils/DateUtil.ets
├── build.gradle.kts         # 根构建配置
├── settings.gradle.kts      # 模块声明 + 阿里云镜像
└── gradle/                  # Gradle Wrapper
```

### 代码共享策略

| 层 | Android | iOS | 鸿蒙 |
|----|---------|-----|------|
| 业务逻辑（模型、选菜算法、菜谱数据） | ✅ core/commonMain 共享 | ✅ core/commonMain 共享 | ✅ ArkTS 移植（逻辑一致） |
| UI 组件 | Compose (app模块) | Compose Multiplatform (计划) | ArkUI (harmonyApp) |
| 视频播放 | Media3 ExoPlayer | AVPlayer (计划) | Video 组件 |
| 日期处理 | kotlinx-datetime | kotlinx-datetime | JavaScript Date |

---

## 二、Android APK 编译

### 环境要求
- JDK 17+
- Android SDK（platform-tools, build-tools;34.0.0, platforms;android-34）
- Gradle 8.9（经 wrapper 自动下载）

### 编译命令（Windows PowerShell）
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:ANDROID_HOME = "c:\Users\sgc\.android\sdk"
.\gradlew.bat assembleDebug --console=plain
```

### 产物
- 路径：`app/build/outputs/apk/debug/app-debug.apk`
- 安装：`adb install app-debug.apk`

### 验证状态
✅ 已在本机 Windows 环境成功编译，APK 可正常安装运行。

---

## 三、iOS IPA 编译

### 环境要求
- **macOS**（必须，Windows 无法编译 iOS）
- Xcode 15+
- Kotlin 2.0.21 + Compose Multiplatform 1.6.11+
- CocoaPods（如需）

### 编译步骤

1. **在 macOS 上打开项目**
```bash
git clone https://github.com/scg927/TasteDaily.git
cd TasteDaily
```

2. **配置 iOS 目标**（需在 app 模块的 build.gradle.kts 中添加）

当前 app 模块仅配置了 Android 目标。要在 macOS 上启用 iOS 编译，需将 app 模块改造为 Compose Multiplatform：

```kotlin
// app/build.gradle.kts 需添加的配置（在 macOS 上操作）
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.android.application")
}

kotlin {
    androidTarget()
    listOf(iosX64(), iosArm64(), iosSimulatorArm64())
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
        val androidMain by getting { /* Android 特有依赖 */ }
        val iosMain by getting { /* iOS 特有依赖 */ }
    }
}
```

3. **生成 Xcode 工程**
```bash
./gradlew :app:linkDebugFrameworkIosArm64
```

4. **用 Xcode 打开 iosApp 并编译**
```bash
open iosApp/TasteDaily.xcodeproj
# 在 Xcode 中选择目标设备/模拟器，点击 Run
```

5. **产出 IPA**（需 Apple Developer 账号）
```bash
xcodebuild archive -scheme TasteDaily -archivePath build/TasteDaily.xcarchive
xcodebuild -exportArchive -archivePath build/TasteDaily.xcarchive \
  -exportPath build/ipa -exportOptionsPlist ExportOptions.plist
```

### 限制说明
- ⚠️ **必须在 macOS 上编译**，Windows 无法生成 IPA
- ⚠️ 当前 app 模块的 UI 使用 AndroidX Compose（非 Compose Multiplatform），需要进一步将 `androidx.compose.*` 替换为 `org.jetbrains.compose.*` 才能跨平台共享 UI
- ✅ core 模块已完成 KMP 改造，iOS 可直接复用业务逻辑

---

## 四、HarmonyOS HAP 编译

### 环境要求
- DevEco Studio 5.0+（鸿蒙官方 IDE）
- HarmonyOS SDK（API 13+）
- 鸿蒙 NEXT 不支持 Kotlin，使用 **ArkTS** 开发

### 编译步骤

1. **下载安装 DevEco Studio**
   - 官网：https://developer.huawei.com/consumer/cn/deveco-studio/
   
2. **导入项目**
   - 打开 DevEco Studio → File → Open → 选择 `harmonyApp/` 目录
   
3. **配置签名**
   - File → Project Structure → Signing Configs → 勾选 Automatically generate signature

4. **编译运行**
   - 点击 Run 按钮，或执行：
```bash
hvigorw assembleHap
```

5. **产物**
   - 路径：`harmonyApp/entry/build/default/outputs/default/entry-default-signed.hap`
   - 安装：`hdc install entry-default-signed.hap`

### 鸿蒙版功能
- ✅ 日历主页（月历视图 + 日期选择）
- ✅ 每日推荐菜（与 Android/iOS 算法一致）
- ✅ 菜谱卡片展示
- ⚠️ 详情页、沉浸式烹饪、视频播放待后续开发

### 限制说明
- ⚠️ HarmonyOS NEXT **不支持 Kotlin**，鸿蒙端使用 ArkTS 独立实现
- ✅ 菜谱数据与选菜算法已移植为 ArkTS（`DishData.ets`），与 core 模块逻辑完全一致
- ⚠️ 腾讯视频已验证 KMP→鸿蒙的可行性（HDC 2026），但需自研渲染桥接，非普通项目可复制

---

## 五、跨平台技术选型说明

### 为什么选 Kotlin Multiplatform？

| 方案 | 跨平台范围 | 优势 | 劣势 |
|------|-----------|------|------|
| **KMP + Compose Multiplatform** | Android + iOS | 官方支持，iOS 已 Stable，共享逻辑+UI | 鸿蒙不支持 |
| Flutter | Android + iOS + 鸿蒙(社区) | 一套 Dart 代码 | 需完全重写现有 Kotlin 代码 |
| React Native | Android + iOS | 生态成熟 | 鸿蒙支持弱，需重写 |
| 鸿蒙原生 ArkTS | 仅鸿蒙 | 官方支持 | 无法跨平台 |

### 最终策略
```
                    ┌─── Android (Kotlin + Compose) ──── APK ✅
                    │
core (KMP 共享) ────┼─── iOS (Compose Multiplatform) ─── IPA (需macOS)
                    │
                    └─── HarmonyOS (ArkTS 移植) ──────── HAP (需DevEco)
```

- **Android + iOS**：共享 core 模块（100% 逻辑复用）+ 计划共享 UI（Compose Multiplatform）
- **鸿蒙**：独立 ArkTS 实现，但菜谱数据与选菜算法保持一致

---

## 六、各端验证清单

| 平台 | 编译 | 安装 | 运行 | 备注 |
|------|------|------|------|------|
| Android | ✅ 已验证 | ✅ 已验证 | ✅ 已验证 | Windows 本机完成 |
| iOS | ⏳ 待验证 | ⏳ 待验证 | ⏳ 待验证 | 需 macOS + Xcode |
| 鸿蒙 | ⏳ 待验证 | ⏳ 待验证 | ⏳ 待验证 | 需 DevEco Studio |

---

## 七、版本历史

| 版本 | 日期 | 说明 |
|------|------|------|
| v1.0.0 | 2026-07-04 | 初始版本，6 道菜 |
| v2.0.0 | 2026-07-05 | 18 道菜 + 日历主页 + 菜谱浏览 |
| v2.0.1 | 2026-07-05 | 修复荣耀/华为视频闪退 |
| v3.0.0 | 2026-07-07 | 跨平台架构（KMP core + iOS 骨架 + 鸿蒙 ArkTS 骨架） |
