package com.vad.pullup.data.db

import androidx.room.TypeConverters
import java.sql.Date

@TypeConverters(DateConvertor::class)
data class RepeatSum(
    val countRepeat: Int,
    val dateRepeat: Date
)
