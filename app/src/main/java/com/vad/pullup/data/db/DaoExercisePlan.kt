package com.vad.pullup.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoExercisePlan {

    @Query("SELECT * FROM exercise_plan WHERE day = :day")
    suspend fun getExerciseOnPlanFromDay(day: Int): ExercisePlan

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    suspend fun getAllExercise(): List<Exercise>

}