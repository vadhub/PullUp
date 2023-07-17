package com.vad.pullup.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_plan")
data class ExercisePlan(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_exercise")
    val idExercise: Int,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "week")
    val week: Int
    )
