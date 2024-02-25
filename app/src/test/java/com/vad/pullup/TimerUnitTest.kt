package com.vad.pullup

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TimerUnitTest {

    private val timer by lazy { Timer() }

    @Test
    fun isStartTimer() {
        val isStart = timer.isStart
        assertEquals(false, isStart)
    }

    @Test
    fun startTimer() {
        timer.start()
        val isStart = timer.isStart
        assertEquals(true, isStart)
    }

    @Test
    fun stopTimer() {
        timer.stop()
        val isStart = timer.isStart
        assertEquals(false, isStart)
    }

    @Test
    fun pauseTimer() {
        timer.pause()
        val isStart = timer.isStart
        assertEquals(false, isStart)
    }

    @Test
    fun restartTimer() {
        timer.restart()
        val isStart = timer.isStart
        assertEquals(true, isStart)
    }

    @Test
    fun setTime_one_second() {
        timer.setTimerInMilliseconds(1000)
        val milliseconds = timer.getMilliseconds()
        assertEquals(1000, milliseconds)
    }

    @Test
    fun setTimerListener() {
        val timerListener: Timer.TimerListener = object : Timer.TimerListener {
            override fun showTimeInMilliseconds(milliseconds: Long) {}
        }
        timer.setTimerListener(timerListener)
        assertNotNull(timer.getTimerListener())
    }

    @Test
    fun `show zero milliseconds from listener`() {
        var millisecond = -1L
        val timerListener = object : Timer.TimerListener {
            override fun showTimeInMilliseconds(milliseconds: Long) {
                millisecond = milliseconds
            }
        }

        timer.setTimerListener(timerListener)
        timerListener.showTimeInMilliseconds(0)
        assertEquals(0, millisecond)
    }
}