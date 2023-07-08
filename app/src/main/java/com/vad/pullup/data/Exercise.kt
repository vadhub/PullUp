package com.vad.pullup.data

import java.sql.Date

data class Exercise(
    private val idExercise: Int,
    private val state: Int,
    private val count: Int,
    private val date: Date
)
