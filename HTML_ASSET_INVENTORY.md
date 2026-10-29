# HTML_ASSET_INVENTORY.md — v2.3 HTML 資源盤點

來源：`japanese_step_by_step_app_v2.3_高清語音修復版.html`（單檔內嵌，無外部本機檔案依賴）

## 內嵌資源
| 資源 | 數量/大小 | 型態 |
|---|---|---|
| 離線語音包 `OFFLINE_JP_AUDIO_PACK` | 原始字串 6,983,695 字元 | 內嵌於 `<script>` |
| └ `mp3List` | **1431 段** MP3（base64） | 每段解碼開頭 `FF F3`（MPEG Layer 3 同步字） |
| └ `keyToIdx` | **2739 組** 文字→音訊對應 | 假名/單字/數字/量詞/會話/例句 |
| 內嵌 data:audio URI | 2 處 | 少量額外音訊引用 |
| 字型 | 系統字型 + FontManifest | 由 WebView 載入 |
| 圖片/圖示 | 內嵌 SVG/emoji 為主 | 無獨立圖檔 |

## 外部依賴（需注意）
| 網域 | 用途 | 離線影響 |
|---|---|---|
| `cdn.tailwindcss.com` | Tailwind CSS（樣式） | 離線時可能無法取得 Play CDN 樣式；功能不依賴（其餘 CSS 已內嵌） |
| `fonts.googleapis.com` / `fonts.gstatic.com` | Google 字型 | 離線時退回系統字型，不影響功能 |
| `translate.googleapis.com` / `translate.google.com` | **雲端語音**（translate_tts） | **僅當使用者切到非 builtin 且連網時使用**；預設為離線高清包 |
| `dict.youdao.com` | 有道字典查詢 | 選用功能，非核心 |

## 離線語音完整性
- mp3List 1431 段為**有效 MP3**（已抽樣解碼驗證 `FF F3` 開頭），與使用者所述「約 1431 組離線語音」相符。
- 語音來源預設 `getVoiceSourceMode() → 'builtin'`，即 `playEmbeddedOfflineJapaneseAudio()`（內嵌高清包）。
- 無獨立音訊檔需額外打包；全部內嵌於 HTML，隨 APK 打包。

## 本報告結論
資源完整性：✔ 離線高清語音包完整內嵌（1431 MP3 + 2739 key 對應）。唯一外部樣式/字型 CDN 在離線時自動退回，不影響核心學習與發音功能。
