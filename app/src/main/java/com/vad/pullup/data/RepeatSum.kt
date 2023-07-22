package com.vad.pullup.data

import androidx.room.TypeConverters
import com.vad.pullup.data.db.DateConvertor
import java.sql.Date

@TypeConverters(DateConvertor::class)
data class RepeatSum(
    val countRepeat: Int,
    val dateRepeat: Date
)
