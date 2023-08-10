package com.vad.pullup.domain.model

interface TimerHandler {
    fun showTime(time: Long)
    fun finishTime()
}