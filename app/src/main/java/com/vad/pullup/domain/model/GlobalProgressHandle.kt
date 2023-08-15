package com.vad.pullup.domain.model

import com.vad.pullup.data.Configuration

class GlobalProgressHandle(private val configuration: Configuration) {

    fun handle() {
        if (configuration.getDay() % 3 == 0) {
            configuration.saveWeek(configuration.getWeek() + 1)
        }
    }

}