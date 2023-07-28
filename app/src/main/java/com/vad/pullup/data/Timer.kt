package com.vad.pullup.data

import android.os.CountDownTimer

data class Timer(val time: Long) {

    private lateinit var handler: TimerHandler
    private lateinit var timer: CountDownTimer
    private var timeStartFrom = time
    private var timeLast = 0L
    private val interval = 100L

    fun setTimerHandler(handler: TimerHandler) {
        this.handler = handler
    }

    fun startTimer() {
        timer = object: CountDownTimer(timeStartFrom, interval) {
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

    fun cancelTimer(): Long {
        timer.cancel()
        return timeLast
    }
}