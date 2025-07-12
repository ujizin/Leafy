package com.ujizin.leafy.alarm.player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer

class AlarmPlayer(
    private val context: Context,
    private val mediaPlayer: MediaPlayer,
) {

    private var countDownTimer: CountDownTimer? = null

    private fun countDownTimer(timeOutInMillis: Long) = object : CountDownTimer(
        timeOutInMillis,
        TICK_IN_MILLISECONDS,
    ) {
        override fun onTick(millisUntilFinished: Long) = Unit /* no-op */
        override fun onFinish() {
            mediaPlayer.pause()
        }
    }

    fun play(uri: Uri, timeOutInMillis: Long = TIME_OUT_IN_MILLISECONDS) {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        with(mediaPlayer) {
            setDataSource(context, uri)
            setAudioAttributes(audioAttributes)
            isLooping = true
            prepareAsync()
            setOnPreparedListener { start() }
        }

        addCountDownTimeout(timeOutInMillis)
    }

    private fun addCountDownTimeout(timeOutInMillis: Long) {
        countDownTimer = countDownTimer(timeOutInMillis)
        countDownTimer?.start()
    }

    fun release() {
        countDownTimer?.cancel()
        mediaPlayer.release()
    }

    companion object {

        private const val TIME_OUT_IN_MILLISECONDS = 120000L
        private const val TICK_IN_MILLISECONDS = 1000L
    }
}