package com.example.aifitnessmirrorapp.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

object FeedbackManager {
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS)
                tts?.language = Locale.US
        }
    }

    fun say(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun release() {
        tts?.shutdown()
    }
}
