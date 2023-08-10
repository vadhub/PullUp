package com.vad.pullup.domain.model

import android.os.CountDownTimer

data class Timer(val time: Long) {

    private lateinit var handler: TimerHandler
    private var timer: CountDownTimer? = null

    var timeStartFrom = time
        private set

    private var timeLast = 0L
    private val interval = 100L

    var isStart = false
        private set

    fun setTimerHandler(handler: TimerHandler) {
        this.handler = handler
    }

    fun startTimer() {
        if (timeStartFrom > 1_000) {
            isStart = true
            timer = object : CountDownTimer(timeStartFrom, interval) {
                override fun onTick(millisUntilFinished: Long) {
                    timeStartFrom = millisUntilFinished
                    handler.showTime(millisUntilFinished)
                    timeLast += interval
                }

                override fun onFinish() {
                    handler.finishTime()
                }
            }.start()
        }
    }

    fun cancelTimer(): Long {
        isStart = false
        timer?.cancel()
        return timeLast
    }
}