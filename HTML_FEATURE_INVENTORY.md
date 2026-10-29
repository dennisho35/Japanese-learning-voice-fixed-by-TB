# HTML_FEATURE_INVENTORY.md — v2.3 HTML 功能盤點

來源：`japanese_step_by_step_app_v2.3_高清語音修復版.html`（7,616,300 bytes / SHA256 `007e58ec…`）
盤點方式：實際掃描 HTML id、onclick、JavaScript 結構，非僅靠介面文字。

## 導覽模組（navTab）
| id | 功能 |
|---|---|
| navTab_home | 首頁：今日分鐘、連續天數(Streak)、每日目標（5/10/15/30 分）、學習統計卡片（假名/數字/會話/測驗）、每日推薦計劃 |
| navTab_kana | 五十音中心 |
| navTab_numbers | 數字/量詞 |
| navTab_conversation | 日常會話 |
| navTab_quiz | 測驗與複習 |

## 核心功能區塊
| 功能 | 線索 | 說明 |
|---|---|---|
| 引導頁/Onboarding | btnOpenOnboarding | 首次使用引導 |
| 設定 | btnOpenSettings | 設定彈窗 |
| 深色模式 | btnToggleDark / themeIcon | 深淺主題切換 |
| 全域讀音輔助 | globalAidsToolbar / btnToggleRomajiGlobal / romajiStatusBadge | 羅馬字開關 |
| 初學/進階預設 | presetBtnBeginner / presetBtnAdvanced | 一鍵預設輔助層級 |
| 讀音輔助層 | chkLayerRomaji / chkLayerNative / chkLayerHira / chkLayerTrans / chkLayerNotes | 羅馬字/本地化讀音/假名/翻譯/註解 圖層 |
| 方言/拼音 | selectPhoneticDialect | 拼音體系選擇 |
| 語速 | headerSpeedGroup / appState.speechRate | 語速控制 |
| 手寫 | canvas（56 處引用） | 假名手寫與筆順練習 |
| 測驗 | quiz 相關（40 處引用） | 測驗題目/答案判斷/計分 |
| 收藏 | 收藏 / favorite（15 處） | 生字收藏 |
| 錯題 | 錯題 / wrong（9 處） | 錯題複習 |
| 進度/統計 | 統計卡片 / homeStat* | 學習統計 |
| 提示 | toastBanner / toastMessage / btnCloseToast | 即時提示 |
| 每日推薦 | dailyRecommendationsList / recommendedPlanSubtitle | 個人化每日計劃 |

## 教材內容
- 五十音（平假名/片假名）、濁音/半濁音、拗音、假名發音
- 詞彙、數字、量詞、日期/月份/星期、時間、助詞與文法、日常會話
- 測驗題庫（含答題、判分）、錯題複習、收藏生字

## 資料儲存
- 使用瀏覽器 localStorage（WebView 已開 domStorageEnabled=true）
- 儲存：語言/讀音偏好、語速、每日目標、學習進度、收藏、錯題、統計

## 本報告結論
以上項目皆以實際 HTML 結構為依據列舉。原 HTML 未改動，直接原樣打包。
