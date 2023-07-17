package com.vad.pullup.data

import com.vad.pullup.data.db.DaoExercisePlan
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import java.io.BufferedReader

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun setAllProgram(listRepeat: List<Repeat>) {

        listRepeat.forEach { daoExercisePlan.insertExerciseProgram(ExercisePlan(0, it.count, it.week)) }
    }

    suspend fun getPlanOfDay(day: Int) = daoExercisePlan.getExerciseOnPlanFromDay(day)

    suspend fun getAllExercise() = daoExercisePlan.getAllExercise()

    suspend fun writeExercise(exercise: Exercise) = daoExercisePlan.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = daoExercisePlan.updateExercise(exercise)

}