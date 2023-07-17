package com.vad.pullup.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoExercisePlan {

    @Query("SELECT * FROM exercise_plan WHERE week = :week")
    suspend fun getExerciseOnPlanFromDay(week: Int): List<ExercisePlan>

    @Query("DELETE FROM exercise_plan")
    suspend fun deleteAll()

    @Insert
    suspend fun insertExerciseProgram(exerciseProgram: ExercisePlan)

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    suspend fun getAllExercise(): List<Exercise>

}