package com.vad.pullup.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity(tableName = "exercise")
@TypeConverters(DateConvertor::class)
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_")
    val idExercise: Int,

    @ColumnInfo(name = "state")
    val state: Int,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "date")
    val date: Date
)
