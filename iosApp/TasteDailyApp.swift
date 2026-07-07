//
//  TasteDailyApp.swift
//  TasteDaily iOS 入口
//
//  此文件是 iOS 端的入口点。
//  Compose Multiplatform 的 UI 通过 KotlinFramework 桥接到 iOS。
//  需在 macOS + Xcode 环境下编译，产出 .ipa 文件。
//

import SwiftUI
import ComposeKit  // Kotlin Multiplatform 编译产物

@main
struct TasteDailyApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    var body: some View {
        // ComposeMultiplatformView 是 Kotlin/Native 编译的 UIKit 桥接
        ComposeMultiplatformView()
            .ignoresSafeArea()
    }
}
