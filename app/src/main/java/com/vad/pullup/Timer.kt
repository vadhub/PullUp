package com.vad.pullup

class Timer {

    private var timerListener: TimerListener? = null

    private var milliseconds = 0L

    var isStart = false
        private set

    fun start() {
        isStart = true
    }

    fun stop() {
        isStart = false
    }

    fun pause() {
        isStart = false
    }

    fun restart() {
        isStart = true
    }

    fun setTimerInMilliseconds(milliseconds: Long) {
        this.milliseconds = milliseconds
    }

    fun getMilliseconds() = milliseconds

    fun setTimerListener(timerListener: TimerListener) {
        this.timerListener = timerListener
    }

    fun getTimerListener() = timerListener

    interface TimerListener {
        fun showTimeInMilliseconds(milliseconds: Long)
        fun finishTimer()
    }
}