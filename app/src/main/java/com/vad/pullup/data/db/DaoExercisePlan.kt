package com.vad.pullup.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.ExercisePlan
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.domain.model.entity.RepeatSum

@Dao
interface DaoExercisePlan {

    @Query("SELECT * FROM exercise_plan WHERE week = :week")
    suspend fun getExerciseOnPlanFromWeek(week: Int): List<ExercisePlan>

    @Query("DELETE FROM exercise_plan")
    suspend fun deleteAll()

    @Query("SELECT SUM(count_) as countRepeat, date_ as dateRepeat FROM exercise GROUP BY date(date_/1000, 'unixepoch')")
    suspend fun sumGroupByDate(): List<RepeatSum>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseProgram(exercisePrograms: List<ExercisePlan>) : LongArray

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise ORDER BY date(date_/1000, 'unixepoch') ASC")
    suspend fun getAllExercise(): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgramLineItem(programItem: List<ProgramItem>)

    @Query("SELECT * FROM program_item")
    suspend fun getAllProgramItem(): List<ProgramItem>

    @Query("DELETE FROM exercise WHERE date(date_/1000, 'unixepoch') = date(:date/1000, 'unixepoch')")
    suspend fun deleteByDate(date: Long)

}