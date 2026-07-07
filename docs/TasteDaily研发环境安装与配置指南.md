# TasteDaily Android 研发环境安装与配置指南

> 文档版本：1.0 ｜ 生成日期：2026-07-05 ｜ 适用项目：TasteDaily（Kotlin 2.0.21 + Jetpack Compose + Material3）

---

## 一、当前环境评估结论

### 1.1 核心结论

**你当前的环境属于"命令行编译可用、但研发体验不完整"的状态**，即：能编译出 APK，但缺少 IDE 可视化开发与模拟器调试能力。具体如下：

| 能力 | 当前状态 | 说明 |
|------|----------|------|
| 命令行编译 APK | ✅ 可用 | 已通过 `gradlew assembleDebug` 成功生成 APK |
| 真机调试 | ✅ 可用 | 有 platform-tools/adb，可连接手机安装 |
| IDE 可视化开发 | ⚠️ 受限 | 仅有 IntelliJ IDEA 社区版，无 Android Studio 的布局预览/集成调试 |
| 模拟器调试 | ❌ 缺失 | 未安装 emulator 与 AVD，无法脱离真机测试 |
| 环境变量持久化 | ❌ 缺失 | ANDROID_HOME/JAVA_HOME 未持久化，每次编译需手动设置 |

### 1.2 环境组件清单

| 组件 | 已安装情况 | 是否需配置 |
|------|------------|------------|
| JDK 17 | ✅ `C:\Program Files\Java\jdk-17` | 需设为 JAVA_HOME |
| JDK 11 | ✅ `C:\Program Files\Java\jdk-11` | 当前误设为 JAVA_HOME，需改 |
| Android SDK | ✅ `c:\Users\sgc\.android\sdk`（platform-tools、build-tools 34.0.0、platforms;android-34、cmdline-tools） | 需设 ANDROID_HOME |
| Android Studio | ❌ 未安装 | 建议安装（研发体验核心） |
| IntelliJ IDEA | ✅ 2024.1.3（含 Kotlin 插件） | 可临时替代，但功能受限 |
| Gradle 8.9 | ✅ 经 wrapper 自动下载 | 无需单独安装 |
| Kotlin 2.0.21 | ✅ 经 Gradle 插件编译 | 无需单独安装 |
| Git | ✅ 2.40.0 | 已配置 |
| GitHub CLI | ✅ 2.96.0 | 已登录（scg927） |
| Emulator/AVD | ❌ 未安装 | 需安装（可选） |

---

## 二、完整研发环境安装步骤

按下列顺序操作，可将本机升级为完整的 Android 研发环境。

### 步骤 1：修正 JDK 环境变量（必做）

Android Gradle Plugin 8.5.x 要求 JDK 17+。当前 JAVA_HOME 错误指向 jdk-11，会导致 sdkmanager 报 `UnsupportedClassVersionError`。

**操作（PowerShell 管理员）：**

```powershell
# 永久设置 JAVA_HOME 为 JDK 17
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-17", "User")

# 将 JDK 17 的 bin 加入 PATH（置于前列，覆盖 jdk-11）
$path = [Environment]::GetEnvironmentVariable("Path", "User")
if ($path -notlike "*jdk-17\bin*") {
    [Environment]::SetEnvironmentVariable("Path", "C:\Program Files\Java\jdk-17\bin;$path", "User")
}

# 验证（需重开 PowerShell 窗口）
java -version   # 应显示 17.x
echo $env:JAVA_HOME  # 应显示 C:\Program Files\Java\jdk-17
```

### 步骤 2：持久化 Android SDK 环境变量（必做）

当前 SDK 已装在 `c:\Users\sgc\.android\sdk`，但环境变量未持久化，每次编译都要手动设。

**操作（PowerShell 管理员）：**

```powershell
$sdk = "c:\Users\sgc\.android\sdk"
[Environment]::SetEnvironmentVariable("ANDROID_HOME", $sdk, "User")
[Environment]::SetEnvironmentVariable("ANDROID_SDK_ROOT", $sdk, "User")

# 将 platform-tools 与 cmdline-tools 加入 PATH
$path = [Environment]::GetEnvironmentVariable("Path", "User")
$newPaths = "$sdk\platform-tools;$sdk\cmdline-tools\latest\bin"
if ($path -notlike "*platform-tools*") {
    [Environment]::SetEnvironmentVariable("Path", "$newPaths;$path", "User")
}

# 验证（重开窗口后）
adb version          # 应显示 ADB 版本
sdkmanager --version # 应显示 sdkmanager 版本
echo $env:ANDROID_HOME  # 应显示 SDK 路径
```

### 步骤 3：安装 Android Studio（强烈推荐）

Android Studio 是 Android 官方 IDE，提供 Compose 布局预览、一键运行调试、模拟器管理、APK 分析等核心研发能力。

**下载安装：**

1. 访问官方下载页：https://developer.android.com/studio
2. 下载 Windows 版安装包（约 1.2 GB）
3. 运行安装程序，保持默认选项（会自动配置 SDK 与模拟器）
4. 首次启动时，Android Studio 会引导完成 SDK 与虚拟设备的初始化

**安装后配置：**

- 打开 Android Studio → `File` → `Settings` → `Languages & Frameworks` → `Kotlin`
- 确认 Kotlin 插件版本 ≥ 2.0.21
- 打开项目 `TasteDaily`，IDE 会自动识别 Gradle 同步

> 注：安装 Android Studio 后，它会自带一个 JDK（通常在 `C:\Program Files\Android\Android Studio\jbr`），可在 IDE 内设置使用，无需与系统 JAVA_HOME 冲突。

### 步骤 4：安装模拟器（可选，但推荐）

模拟器让你脱离真机即可调试。

**方式 A：通过 Android Studio（推荐）**
- Android Studio → `Tools` → `Device Manager` → `Create Device` → 选择 Pixel 系列 → 下载系统镜像（API 34）→ 完成

**方式 B：通过命令行**
```powershell
$sdk = $env:ANDROID_HOME
# 安装模拟器与系统镜像
sdkmanager "emulator" "system-images;android-34;google_apis;x86_64"
# 接受许可
sdkmanager --licenses
# 创建 AVD（虚拟设备）
avdmanager create avd -n taste_daily_avd -k "system-images;android-34;google_apis;x86_64" -d pixel_6
# 启动模拟器
emulator -avd taste_daily_avd
```

### 步骤 5：验证研发环境

打开新的 PowerShell 窗口（让环境变量生效），执行：

```powershell
# 1. JDK
java -version              # 期望：17.x
echo $env:JAVA_HOME        # 期望：C:\Program Files\Java\jdk-17

# 2. Android SDK
echo $env:ANDROID_HOME     # 期望：c:\Users\sgc\.android\sdk
adb version                # 期望：Android Debug Bridge 1.0.41
sdkmanager --list_installed # 期望：列出 platform-tools、build-tools;34.0.0、platforms;android-34

# 3. 编译项目
cd <项目根目录>
.\gradlew.bat assembleDebug --console=plain
# 期望：BUILD SUCCESSFUL，输出 app/build/outputs/apk/debug/app-debug.apk

# 4. 真机安装（USB 调试已开启）
adb install app\build\outputs\apk\debug\app-debug.apk
# 期望：Success

# 5. 模拟器安装（若已创建 AVD）
emulator -avd taste_daily_avd &
adb install app\build\outputs\apk\debug\app-debug.apk
```

---

## 三、技术栈版本对应关系

项目使用的具体版本如下（见 `build.gradle.kts` 与 `gradle-wrapper.properties`）：

| 技术 | 版本 | 配置位置 | 说明 |
|------|------|----------|------|
| Kotlin | 2.0.21 | 根 `build.gradle.kts` 插件声明 | 经 Gradle Kotlin 插件编译，无需单独安装 kotlinc |
| Jetpack Compose | BOM 2024.06.00 | `app/build.gradle.kts` | Material3 组件库 |
| Material3 | 随 Compose BOM | `app/build.gradle.kts` | Material Design 3 组件 |
| Android Gradle Plugin | 8.5.2 | 根 `build.gradle.kts` | 构建工具 |
| Gradle | 8.9 | `gradle-wrapper.properties` | 经 wrapper 自动下载，无需单独安装 |
| compileSdk / targetSdk | 34 | `app/build.gradle.kts` | 对应 Android 14 |
| minSdk | 24 | `app/build.gradle.kts` | 最低支持 Android 7.0 |
| Java 兼容版本 | 17 | `app/build.gradle.kts` | `sourceCompatibility`/`targetCompatibility` |

**关于 Kotlin/Compose 的说明：**

- **Kotlin 2.0.21** 通过 Gradle 插件 `org.jetbrains.kotlin.android` 版本 2.0.21 自动下载并编译，无需在系统单独安装 `kotlinc`。这也是为什么 `kotlinc` 不在 PATH 上——这是正常配置，不是缺失。
- **Jetpack Compose** 通过 `org.jetbrains.kotlin.plugin.compose` 插件与 Compose BOM 引入，编译器与 Kotlin 版本绑定，由 Gradle 统一管理。
- **结论：你的 Kotlin/Compose 环境是完整的**，版本由 Gradle 工具链统一管理，已成功编译 APK 即为证明。

---

## 四、常见问题排查

### 4.1 sdkmanager 报 UnsupportedClassVersionError

**现象：**
```
java.lang.UnsupportedClassVersionError: ... has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to 55.0
```

**原因：** JAVA_HOME 指向 JDK 11，sdkmanager 需要 JDK 17+。

**解决：** 按步骤 1 将 JAVA_HOME 改为 `C:\Program Files\Java\jdk-17`。

### 4.2 processDebugResources 报 AAPT 链接失败

**现象：**
```
AAPT: stableIds.txt: error: failed to open
```

**原因：** 项目路径过长触发 Windows 文件系统限制。

**解决：** 将项目移到短路径下编译，如 `c:\Users\sgc\.android\tdbuild`（本文档对应项目已采用此方案）。

### 4.3 Gradle 下载缓慢

**原因：** 默认从 `services.gradle.org` 下载，国内访问慢。

**解决：** 已在 `settings.gradle.kts` 配置阿里云镜像：
```kotlin
maven { url = uri("https://maven.aliyun.com/repository/google") }
maven { url = uri("https://maven.aliyun.com/repository/central") }
```

### 4.4 adb 找不到设备

**排查：**
```powershell
adb devices          # 查看已连接设备
```
- 若列表为空：手机需开启"开发者选项"→"USB 调试"，并用数据线连接
- 若显示 `unauthorized`：手机上弹窗点"允许"
- 若显示 `offline`：重启 adb：`adb kill-server; adb start-server`

---

## 五、研发工作流（环境就绪后）

### 5.1 命令行方式（已验证可用）

```powershell
cd <项目根目录>
.\gradlew.bat assembleDebug          # 编译 debug APK
.\gradlew.bat installDebug           # 编译并安装到已连接设备
.\gradlew.bat clean                  # 清理构建产物
.\gradlew.bat test                   # 运行单元测试
```

### 5.2 IDE 方式（安装 Android Studio 后）

1. `File` → `Open` → 选择项目根目录
2. 等待 Gradle 同步完成
3. 选择运行设备（真机或模拟器）
4. 点击 ▶ 运行，或点击 🐞 调试

### 5.3 版本迭代流程

```powershell
# 修改代码后
git add -A
git commit -m "feat: 新功能描述"
# 升级 app/build.gradle.kts 中的 versionCode/versionName
git tag -a v2.1.0 -m "版本说明"
git push
git push --tags
```

---

## 六、最小可用研发环境清单

若只想"能编译、能装到手机"，满足以下即可（你当前已具备）：

- [x] JDK 17
- [x] Android SDK（platform-tools + build-tools;34.0.0 + platforms;android-34）
- [x] 项目自带的 gradlew wrapper
- [ ] JAVA_HOME 持久化指向 JDK 17（**需配置**）
- [ ] ANDROID_HOME 持久化指向 SDK（**需配置**）

若要"能可视化开发、能调试"，还需：

- [ ] Android Studio
- [ ] 模拟器（AVD）

---

## 七、实际验证结果（2026-07-05 执行）

### 7.1 环境变量持久化验证

| 变量 | 注册表持久值 | 结果 |
|------|-------------|------|
| `JAVA_HOME` | `C:\Program Files\Java\jdk-17` | ✅ 已持久化 |
| `ANDROID_HOME` | `c:\Users\sgc\.android\sdk` | ✅ 已持久化 |
| `ANDROID_SDK_ROOT` | `c:\Users\sgc\.android\sdk` | ✅ 已持久化 |
| `Path` 含 `jdk-17\bin` | 是 | ✅ 已持久化 |
| `Path` 含 `platform-tools` | 是 | ✅ 已持久化 |

> 注：持久化值对新打开的终端窗口生效。当前已打开的旧窗口需关闭重开才能读到新值。

### 7.2 工具链验证

| 验证项 | 命令 | 结果 |
|--------|------|------|
| JDK 版本 | `java -version` | ✅ `java version "17.0.7" 2023-04-18 LTS` |
| JAVA_HOME | `echo $env:JAVA_HOME` | ✅ `C:\Program Files\Java\jdk-17` |
| adb | `adb version` | ✅ `Android Debug Bridge version 1.0.41` |
| sdkmanager | `sdkmanager --version` | ✅ `12.0`（JDK 17 下正常，不再报 UnsupportedClassVersionError） |
| Git | `git --version` | ✅ `git version 2.40.0.windows.1` |

### 7.3 编译验证

```
> Task :app:assembleDebug
BUILD SUCCESSFUL in 1m 3s
36 actionable tasks: 36 up-to-date
```

✅ `gradlew assembleDebug` 编译成功，APK 正常生成。

### 7.4 验证结论

**研发环境已配置为可用状态：**
- JDK 17 ✅（已持久化为 JAVA_HOME，sdkmanager 正常运行）
- Android SDK ✅（platform-tools、build-tools、platforms、cmdline-tools 齐全，已持久化 ANDROID_HOME）
- 命令行编译 ✅（BUILD SUCCESSFUL）
- 真机调试 ✅（adb 可用）
- Git 版本控制 ✅

**如需完整的 IDE 研发体验（可视化布局预览、断点调试、模拟器），按文档第三、四步安装 Android Studio 与 AVD 即可。**
