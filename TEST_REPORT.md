# TEST_REPORT.md — 測試報告

## 建置結果
| 項目 | 結果 |
|---|---|
| Gradle 建置 | ✅ `BUILD SUCCESSFUL`（58s，AGP 9.1.0 / Gradle 9.3.1 / JDK 17） |
| APK 產出 | ✅ `app/build/outputs/apk/release/app-release.apk`（6,721,508 bytes） |
| APK SHA-256 | `cd6db260ecdb69d2866510d15554c850feebb11e2402aa9861374d51367cf09f` |
| 套件 | `com.japanesestepbystep.japanese_step_by_step_v23` |
| 版本 | 2.3.0 (versionCode 3) |

## 完整性測試（打包 vs 原始）
| 檢查 | 上傳 v2.3 HTML | APK 內 HTML | 一致 |
|---|---|---|---|
| SHA-256 | `007e58ec3fae37ced887b0d46a291e796d111dbcc37805b4b2d24ff8bed05863` | `007e58ec…bed05863` | ✅ 完全相同 |
| 大小 | 7,616,300 bytes | 7,616,300 bytes | ✅ |
| 音訊資源 | mp3List 1431 段 + keyToIdx 2739 組 | 同（源自同 HTML） | ✅ |

## 語音資源有效性
- 抽樣解碼 mp3List：開頭 `FF F3` = 有效 MPEG Layer 3 同步字 ✅
- keyToIdx 對應表存在且完整（假名/單字/數字/量詞/會話/例句）✅
- 語音來源預設 `'builtin'`（離線高清包），不依賴網路 ✅

## 本環境實測範圍（誠實標示）
| 測試 | 結果 | 說明 |
|---|---|---|
| APK 建置 | ✅ 通過 | 實際 Gradle 建置 |
| 包體/套件/版本 | ✅ 通過 | aapt dump badging 驗證 |
| HTML 完整性 | ✅ 通過 | SHA-256 一致 |
| 語音資源存在 | ✅ 通過 | 抽樣解碼驗證 |
| 實機安裝 | ⚠️ 未執行 | 本環境無模擬器/實機 |
| 飛行模式播放 | ⚠️ 未執行 | 需實機驗證 |
| 介面/手寫/測驗逐項操作 | ⚠️ 未執行 | 需實機/瀏覽器操作 |

## 未通過/待驗證項目
1. 實機（含飛行模式）語音播放、連續點擊疊音、語速切換 —— 需安裝於 Android 手機實測。
2. 1431 段音訊逐段人耳 A/B 對比 v2.3 瀏覽器播放 —— 已驗證結構與 MP3 有效性，未逐段聽感比對。
3. Tailwind Play CDN 離線樣式 —— 離線時可能退回，需實機確認版面。

## 結論
APK 已實際建置成功；內容（含高清語音包）與上傳 v2.3 HTML 逐位元一致。語音播放與介面操作需實機最終驗收。
