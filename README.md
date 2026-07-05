# TasteDaily 味道日历

每日推荐一道菜的中式美食 App，基于 Jetpack Compose + Material3 构建。

## 版本历史

| 版本 | 说明 |
|------|------|
| v1.0.0 | 初始版本，6 道菜谱，每日推荐 + 沉浸式烹饪 + 视频播放 |
| v2.0.0 | 新增 12 道菜谱（共 18 道）、日历主页、菜谱浏览页、底部导航、版本控制 |

## 技术栈

- Kotlin 2.0.21 + Jetpack Compose + Material3
- AndroidX Navigation Compose（底部导航 + 页面跳转）
- Media3 ExoPlayer（视频播放）
- Coil（图片加载）
- Gradle 8.9 + AGP 8.5.2

## 模块

- `core`：领域模型、每日选菜算法、菜谱种子数据
- `app`：UI 层（日历、菜谱浏览、详情、沉浸式烹饪、视频）

## 构建

`
./gradlew assembleDebug
`
APK 输出：`app/build/outputs/apk/debug/app-debug.apk`
