package com.japanesestepbystep.japanese_step_by_step_web

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import java.util.Locale

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private lateinit var webView: WebView
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 優先啟動 Google 原生離線日語語音引擎
        tts = TextToSpeech(this, this, "com.google.android.tts")

        webView = WebView(this)
        setContentView(webView)

        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.allowFileAccess = true

        // 掛載 window.AndroidTTS 橋接供 HTML 直接呼叫手機原生離線日語語音
        webView.addJavascriptInterface(AndroidTtsBridge(), "AndroidTTS")
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("file:///android_asset/japanese_step_by_step_app.html")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.JAPAN)
            ttsReady = (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED)
        }
    }

    inner class AndroidTtsBridge {
        @JavascriptInterface
        fun speak(text: String, rateStr: String?) {
            if (!ttsReady || tts == null) return
            val rate = rateStr?.toFloatOrNull() ?: 0.85f
            tts?.setPitch(1.0f)
            tts?.setSpeechRate(rate.coerceIn(0.65f, 1.15f))
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "jp_utt_${System.currentTimeMillis()}")
        }
    }

    override fun onBackPressed() {
        if (this::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
