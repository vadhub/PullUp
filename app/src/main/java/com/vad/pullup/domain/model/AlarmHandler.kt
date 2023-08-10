package com.vad.pullup.domain.model

import android.content.Context
import android.media.MediaPlayer
import com.vad.pullup.R
import java.io.IOException


class AlarmHandler(context: Context?) {
    private var mediaPlayer: MediaPlayer?

    init {
        mediaPlayer = MediaPlayer.create(context, R.raw.rington)
    }

    fun playAlarm() {
        if (mediaPlayer != null) {
            mediaPlayer!!.start()
        }
    }

    fun stopAlarm() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            try {
                mediaPlayer!!.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun cancelAlarm() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
}