# 日語一步通 v2.3 高清語音版

將 **japanese_step_by_step_app_v2.3_高清語音修復版.html** 完整轉換為 Android APK 的原生專案。

## 套件與版本
| 項目 | 值 |
|---|---|
| applicationId | `com.japanesestepbystep.japanese_step_by_step_v23` |
| 顯示名稱 | 日語一步通 v2.3 |
| versionName / versionCode | 2.3.0 / 3 |
| minSdk / targetSdk / compileSdk | 24 / 36 / 36 |
| 技術 | Android 原生 Kotlin + WebView |

## 建置
```bash
export ANDROID_HOME=<Android SDK 路徑>
export JAVA_HOME=<JDK 17 路徑>
./gradlew assembleRelease        # 產出 app/build/outputs/apk/release/app-release.apk
./gradlew assembleDebug          # 產出 debug 版
```

## 功能
- 原生 WebView 載入 APK 內嵌的 v2.3 HTML（`assets/japanese_step_by_step_app.html`），完整保留四大模組、題庫、手寫畫布與介面。
- `javaScriptEnabled=true`、`domStorageEnabled=true`，支援 localStorage 學習進度/收藏/錯題/設定。
- 離線高清語音：HTML 內嵌 **1431 段 MP3**（`OFFLINE_JP_AUDIO_PACK.mp3List`）+ `keyToIdx` 2739 組對應，預設語音來源即此高清包。
- 原生 `AndroidTTS` 橋接：`window.AndroidTTS.speak(text, rate)` 供 HTML 呼叫系統日語 TTS。
- 系統返回鍵處理：WebView 可回退時 goBack()，否則結束。

## 交付物
- APK：`日語一步通_v2.3_高清語音版.apk`（SHA256 `cd6db260ecdb69d2866510d15554c850feebb11e2402aa9861374d51367cf09f`）
- 完整專案原始碼（本目錄）
- 報告：HTML_FEATURE_INVENTORY.md、HTML_ASSET_INVENTORY.md、VOICE_SYSTEM_ANALYSIS.md、ANDROID_COMPATIBILITY_REPORT.md、VERSION_COMPARISON.md、TEST_REPORT.md、BUILD_INSTRUCTIONS.md

## 簽署
以 debug key 簽署（可直接安裝測試）。**非正式發布簽署**——正式上架需另設 release keystore 與 signingConfig。
