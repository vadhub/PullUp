package com.vad.pullup.domain.model

import androidx.room.TypeConverters
import java.sql.Date

@TypeConverters(DateConvertor::class)
data class RepeatSum(
    val countRepeat: Int,
    val dateRepeat: Date
)
