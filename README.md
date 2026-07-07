# TasteDaily 味道日历

> 每日推荐一日三餐菜谱的中式美食 App，支持 Android / iOS / 鸿蒙三端。

## 版本历史

| 版本 | 日期 | 说明 |
|------|------|------|
| v1.0.0 | 2026-07-04 | 初始版本，6 道菜谱，每日推荐 + 沉浸式烹饪 + 视频播放 |
| v2.0.0 | 2026-07-05 | 新增 12 道菜谱（共 18 道）、日历主页、菜谱浏览页、底部导航、版本控制 |
| v2.0.1 | 2026-07-05 | 修复荣耀/华为机型点击视频控件闪退（三层异常防御） |
| v3.0.0 | 2026-07-07 | 跨平台架构改造（KMP core + iOS 骨架 + 鸿蒙 ArkTS 骨架） |
| v4.0.0 | 2026-07-08 | 一日三餐推荐、武汉特色菜谱 30 道（共 48 道）、采购清单与成本估算 |

## 架构

```
                    ┌─── Android (Kotlin + Compose) ──── APK ✅ 已验证
                    │
core (KMP 共享) ────┼─── iOS (Compose Multiplatform) ─── IPA (需 macOS)
                    │
                    └─── HarmonyOS (ArkTS) ──────────── HAP (需 DevEco)
```

### 模块说明

| 模块 | 说明 |
|------|------|
| `core/` | Kotlin Multiplatform 共享模块（模型、选菜算法、菜谱数据），Android 与 iOS 共享 |
| `app/` | Android 应用（日历主页、三餐卡片、菜谱浏览、详情、采购清单、沉浸式烹饪、视频播放） |
| `iosApp/` | iOS 应用骨架（SwiftUI 入口，桥接 Compose Multiplatform） |
| `harmonyApp/` | 鸿蒙应用骨架（ArkTS + ArkUI，菜谱数据与选菜算法与 core 一致） |
| `docs/` | 文档（研发环境配置指南、跨平台编译指南） |

## 技术栈

- Kotlin 2.0.21 + Jetpack Compose + Material3（Android）
- Kotlin Multiplatform + kotlinx-datetime（跨平台共享逻辑）
- Compose Multiplatform 1.6.11（iOS，需 macOS 编译）
- ArkTS + ArkUI（鸿蒙，需 DevEco Studio 编译）
- AndroidX Navigation Compose（底部导航 + 页面跳转）
- Media3 ExoPlayer（视频播放，含异常防御）
- Coil（图片加载）
- Gradle 8.9 + AGP 8.5.2

## 功能

- **日历主页**：月历视图，点击任意日期查看当日三餐安排
- **三餐卡片**：早餐/中餐/晚餐各弹出独立卡片，每餐 3-4 道菜，支持点击进入每餐详情
- **菜谱浏览**：48 道菜平铺展示（含 30 道武汉特色菜），点击进入详情
- **武汉特色菜谱**：涵盖热干面、三鲜豆皮、面窝、排骨藕汤、臭鳜鱼等本地早中晚餐各 10 道
- **成本估算**：每道菜详情页新增"成本"标签，展示食材单价与总成本
- **采购清单**：独立页面列出每道菜的食材用量与价格，自动计算总成本
- **每日推荐**：确定性算法，同一日期全员看到同样的三餐安排
- **沉浸式烹饪**：分步指引，含图片/视频
- **视频播放**：ExoPlayer + 三层异常防御（组件级/页面级/全局兜底）

## 构建

### Android APK（Windows）

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:ANDROID_HOME = "<你的 SDK 路径>"
.\gradlew.bat assembleDebug --console=plain
```

APK 输出：`app/build/outputs/apk/debug/app-debug.apk`

### iOS IPA（macOS）

```bash
./gradlew :app:linkDebugFrameworkIosArm64
open iosApp/TasteDaily.xcodeproj
# 在 Xcode 中编译运行
```

### 鸿蒙 HAP（DevEco Studio）

用 DevEco Studio 打开 `harmonyApp/` 目录，点击 Run。

详细步骤见 [跨平台编译指南](docs/TasteDaily跨平台编译指南.md)。

## 下载

| 平台 | 下载 |
|------|------|
| Android APK | [v4.0.0 Release](https://github.com/scg927/TasteDaily/releases/tag/v4.0.0) |
| 源码 | [v4.0.0 Source](https://github.com/scg927/TasteDaily/releases/tag/v4.0.0) |

## 环境配置

首次搭建开发环境请参考 [研发环境安装与配置指南](docs/TasteDaily研发环境安装与配置指南.md)。
