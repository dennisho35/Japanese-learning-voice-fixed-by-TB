# VOICE_SYSTEM_ANALYSIS.md — 語音系統分析（v2.3）

## 一、語音來源優先順序（以 v2.3 HTML 實作為準）
```
speakJapaneseText(text, slow)
 └─ 若 voiceSource==='builtin' 或 離線(navigator.onLine===false)
 │     → playEmbeddedOfflineJapaneseAudio(text)   ← 內嵌高清離線 MP3 包（預設主路徑）
 └─ 否則（非 builtin 且連網）
       → playCloudJapaneseTts(text)                ← 雲端 translate_tts
```

**結論**：預設語音來源即內嵌高清包（`getVoiceSourceMode()` 預設回傳 `'builtin'`），完全離線、不依賴網路。此即使用者要求的「高清語音包為唯一語音來源」。

## 二、離線高清語音包（OFFLINE_JP_AUDIO_PACK）
- 儲存位置：HTML 內 `<script>` 的 `OFFLINE_JP_AUDIO_PACK` 物件。
- 結構：`{ keyToIdx: {…2739 組…}, mp3List: [ …1431 段 base64… ] }`
- 格式：MPEG Layer 3（已抽樣解碼，首 bytes `FF F3` 為有效 MP3 同步字）。
- 播放：`playEmbeddedOfflineJapaneseAudio()` 以 Web Audio API（AudioContext + AudioBufferSource）解碼播放；`resolveOfflineMp3Chunks()` 依文字經 keyToIdx 對應到 mp3List chunk；支援長句分段拼接與 15ms 間隙。

## 三、AndroidTTS 原生橋接（Android 端）
- HTML 呼叫：`window.AndroidTTS.speak(clean, String(targetRate))`（2 參數）。
- Android 端（MainActivity.kt）：`webView.addJavascriptInterface(AndroidTtsBridge(), "AndroidTTS")`。
- 橋接實作：
  - `TextToSpeech(this, listener, "com.google.android.tts")`（優先 Google 引擎）
  - `onInit`：`setLanguage(Locale.JAPAN)`，非 `LANG_MISSING_DATA/LANG_NOT_SUPPORTED` 即 ready
  - `speak(text, rateStr)`：`setSpeechRate(rate.coerceIn(0.65, 1.15))`、`setPitch(1.0)`、`QUEUE_FLUSH` 停止前一語音
- 介面相容：已核對 HTML 2 參數呼叫與 Kotlin `speak(text, rateStr)` 完全匹配。

## 四、舊語音（v2.2 怪聲）處理
- 舊 v2.2 APK 為 **Flutter WebView**，其怪聲語音來自其內嵌 8.9MB HTML 的舊音訊。
- **本 v2.3 專案完全以 v2.3 HTML 建置，從未引入 v2.2 的 HTML 或音訊**；APK 內語音資源 = v2.3 HTML 的 mp3List 1431 段高清包。
- 無舊語音 fallback：HTML 內不含任何「切到舊語音包」的邏輯；唯一降級路徑是連網時才可用的雲端 TTS（且預設不啟用）。

## 五、語音互斥與可靠性
- `activeTtsSessionId` + `stopAllJapaneseAudio()`：新播放先停止舊播放，避免疊音。
- `lastSpeakSignature` + 60ms 去重：防止快速連按重複播放。
- `speechWatchdogTimer`（600ms）：雲端語音未啟動即回退離線，避免離線時卡在雲端等待。
- `QUEUE_FLUSH`：原生 TTS 播放前先清除前一段。

## 六、語速
- HTML `appState.speechRate`（0.55–0.95 區間）、slow 旗標。
- 原生 TTS `setSpeechRate` 限定 0.65–1.15（Android 引擎實際支援範圍）；HTML 離線 MP3 播放用 `playbackRate` 0.65–1.0。介面語速選擇會如實映射，未改變按鈕即聲稱生效。
