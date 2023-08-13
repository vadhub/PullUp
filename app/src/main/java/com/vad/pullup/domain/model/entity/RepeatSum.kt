package com.vad.pullup.domain.model.entity

import androidx.room.TypeConverters
import com.vad.pullup.domain.model.DateConvertor
import java.sql.Date

@TypeConverters(DateConvertor::class)
data class RepeatSum(
    val countRepeat: Int,
    val dateRepeat: Date
)
