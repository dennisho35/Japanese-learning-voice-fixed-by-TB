# ANDROID_COMPATIBILITY_REPORT.md — Android 相容性報告

## 專案技術
原生 Android Kotlin + WebView，非 Flutter、非「改副檔名」。

## WebView 設定（MainActivity.kt）
| 設定 | 值 | 理由 |
|---|---|---|
| javaScriptEnabled | true | 原 HTML 互動必要 |
| domStorageEnabled | true | localStorage 學習紀錄 |
| databaseEnabled | true | 資料庫儲存 |
| mediaPlaybackRequiresUserGesture | false | 語音播放不強制手勢 |
| allowFileAccess | true | 載入本機 assets |
| 載入來源 | `file:///android_asset/japanese_step_by_step_app.html` | 完全本機，不依賴 GitHub Pages |

## JavaScript 橋接安全
- 僅暴露 `AndroidTtsBridge`（`speak(text, rate)` 一個方法），方法以 `@JavascriptInterface` 標註。
- 未無條件開放任意檔案讀寫或不受限橋接。

## 權限（AndroidManifest）
| 權限 | 用途 |
|---|---|
| INTERNET | 雲端語音（僅使用者選用且連網時） |
| RECORD_AUDIO | 手寫/語音辨識類功能（原 HTML 宣告） |
| queries TTS_SERVICE | Android 11+ 調用系統 TTS 引擎必需 |

## 相容性要點
| 項目 | 狀態 |
|---|---|
| 直向/響應式 | ✔ HTML viewport + 媒體查詢 |
| 返回鍵 | ✔ `onBackPressed`：WebView 可回退時 goBack()，否則結束 |
| 重新開啟/狀態 | ✔ localStorage 持久化，Activity 重建保留 |
| 小螢幕 | ✔ 原 HTML 響應式 |
| 外部 CDN（tailwind/字型） | 離線退回，不影響核心功能 |
| 深色模式 | ✔ HTML btnToggleDark |

## 已發現且需使用者注意的限制
1. **Tailwind Play CDN**（`cdn.tailwindcss.com`）：離線時可能取得不到 Play CDN，若因此樣式受影響，可將 Tailwind 產出之 CSS 內嵌（v2.3 已多為內嵌樣式）。本專案未改 HTML，依 v2.3 原樣打包。
2. **雲端語音**需連網且僅在使用者切到非 builtin 模式時使用；預設離線高清包。
3. **RECORD_AUDIO**：若運行時需動態請求權限，原 HTML 未含權限請求邏輯；實際手寫辨識以離線 canvas 為主，不阻礙核心使用。
