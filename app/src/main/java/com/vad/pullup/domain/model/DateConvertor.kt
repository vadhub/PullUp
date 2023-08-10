package com.vad.pullup.domain.model

import androidx.room.TypeConverter
import java.sql.Date

class DateConvertor {

    @TypeConverter
    fun toDate(date: Long?): Date? {
        return date?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}