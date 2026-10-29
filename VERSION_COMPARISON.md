# VERSION_COMPARISON.md — 新版 v2.3 與舊版 v2.2 差異核對

## 輸入
- 新版：`japanese_step_by_step_app_v2.3_高清語音修復版.html`（7,616,300 bytes）
- 舊版：`日語一步通_v2.2_內置離線語音版.apk`（49,657,679 bytes，Flutter WebView）

## 舊 APK 檢查（僅參考）
| 項目 | 舊 v2.2 |
|---|---|
| applicationId | `com.japanesestepbystep.japanese_step_by_step_web` |
| label | 日語一步通 網頁版 |
| 技術 | Flutter WebView（libflutter/libapp.so） |
| 內嵌 HTML | 8,972,975 bytes（`assets/flutter_assets/assets/japanese_step_by_step_app.html`）|
| 語音來源 | 舊版內嵌語音（使用者指為怪聲來源） |

## 差異與處理
| 項目 | 舊 v2.2 | 新 v2.3 | 處理 |
|---|---|---|---|
| 語音來源 | 舊內嵌語音包 | v2.3 高清語音包（1431 MP3） | **只用 v2.3**，未引入舊音訊 |
| HTML | 8.9MB 舊版 | 7.6MB v2.3 | 用 v2.3 原樣 |
| applicationId | `..._web` | `com.japanesestepbystep.japanese_step_by_step_v23` | 獨立，可並存 |
| 顯示名稱 | 網頁版 | 日語一步通 v2.3 | 更新 |
| 版本 | 1.0.0 (vc1) | 2.3.0 (vc3) | 更新 |
| 技術 | Flutter WebView | 原生 WebView | 精簡、語音橋接更直接 |
| 包體 | 49.6MB | 6.7MB | 原生版更小（HTML 壓縮打包） |

## 新 v2.3 APK 額外實作的 Android 功能
- 原生 `AndroidTTS` 橋接（`window.AndroidTTS.speak`）供 HTML 呼叫系統日語 TTS
- `queries TTS_SERVICE`（Android 11+）
- 返回鍵/WebView 回退處理
- 全新 Logo 啟動器圖示（48–192px）
- 獨立 applicationId 達成與 v2.2 並存

## 語音資源保留
- v2.3 高清包 **1431 段 MP3** + **2739 組 keyToIdx** 完整保留、隨 APK 打包。
- APK 內 HTML 雜湊與上傳 v2.3 一致（見 TEST_REPORT.md）。

## 尚未能驗證項目
- 實體手機安裝與飛行模式實機語音播放（本環境無模擬器/實機，僅完成建置與結構驗證）。
- 1431 段音訊「逐段」與 v2.3 瀏覽器播放做聽感 A/B（已做抽樣 MP3 有效性驗證，未逐段人耳比對）。
