package com.vad.pullup.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DaoExercisePlan {

    @Query("SELECT * FROM exercise_plan WHERE day = :day")
    suspend fun getExerciseOnPlanFromDay(day: Int): ExercisePlan

}