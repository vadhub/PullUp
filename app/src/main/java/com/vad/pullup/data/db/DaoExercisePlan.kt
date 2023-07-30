package com.vad.pullup.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vad.pullup.data.RepeatSum

@Dao
interface DaoExercisePlan {

    @Query("SELECT * FROM exercise_plan WHERE week = :week")
    suspend fun getExerciseOnPlanFromWeek(week: Int): List<ExercisePlan>

    @Query("DELETE FROM exercise_plan")
    suspend fun deleteAll()

    @Query("SELECT SUM(count_) as countRepeat, date_ as dateRepeat FROM exercise GROUP BY date(date_/1000, 'unixepoch')")
    suspend fun sumGroupByDate(): List<RepeatSum>

    @Insert
    suspend fun insertExerciseProgram(exerciseProgram: ExercisePlan)

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    suspend fun getAllExercise(): List<Exercise>

    @Query("SELECT * FROM exercise_plan")
    suspend fun getAllProgram(): List<ExercisePlan>

}