# BUILD_INSTRUCTIONS.md — 建置說明

## 前置需求
| 工具 | 版本 | 本環境實測 |
|---|---|---|
| JDK | 17（Temurin） | ✅ |
| Android SDK | platform 36、build-tools 36.0.0 | ✅ |
| Gradle Wrapper | 9.3.1（內附） | ✅ |
| Android Gradle Plugin | 9.1.0（build.gradle.kts 宣告） | ✅ |
| Kotlin Android Plugin | 2.4.0 | ✅ |

## 環境變數
```bash
export ANDROID_HOME=/path/to/android-sdk
export ANDROID_SDK_ROOT=$ANDROID_HOME
export JAVA_HOME=/path/to/jdk17
export PATH=$JAVA_HOME/bin:$PATH
```

## 建置步驟
```bash
cd JapaneseStepByStep   # 專案根目錄
chmod +x gradlew
./gradlew assembleRelease   # release（以 debug key 簽署，可直接安裝）
# 或
./gradlew assembleDebug     # debug
```

產出位置：`app/build/outputs/apk/release/app-release.apk`

## 正式簽署（上架用）
1. 產生 keystore：`keytool -genkey -v -keystore release.keystore -alias release -keyalg RSA -keysize 2048 -validity 10000`
2. 在 `app/build.gradle.kts` 的 `android.signingConfigs` 新增 release signingConfig，並在 `buildTypes.release` 指定
3. 敏感資訊（keystore 密碼）用環境變數或 CI Secret 注入，**不可寫入 Repository**

## 疑難排除
- 若建置卡住：`pkill -f GradleDaemon; rm -f ~/.gradle/caches/*/journal-1.lock` 後重試
- 若報 SDK 版本錯誤：確認 `local.properties` 的 `sdk.dir` 指向有效 Android SDK

## CI
`build-apk.yml`（GitHub Actions）可自動建 Debug APK 並上傳 artifact。
