package com.vad.pullup.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vad.pullup.domain.model.DateConvertor
import java.sql.Date

@Entity(tableName = "exercise")
@TypeConverters(DateConvertor::class)
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_")
    val idExercise: Int,

    @ColumnInfo(name = "repeat_")
    val repeat: Int,

    @ColumnInfo(name = "count_")
    val count: Int,

    @ColumnInfo(name = "date_")
    val date: Date
)
