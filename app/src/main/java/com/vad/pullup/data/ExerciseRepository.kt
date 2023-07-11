package com.vad.pullup.data

import com.vad.pullup.data.db.DaoExercisePlan
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun setAllProgram() {
        val exercisePlan1: ExercisePlan = ExercisePlan(0, 1, 10, 1)
        val exercisePlan2: ExercisePlan = ExercisePlan(0, 2, 10, 1)
        val exercisePlan3: ExercisePlan = ExercisePlan(0, 3, 10, 1)
        val exercisePlan4: ExercisePlan = ExercisePlan(0, 4, 10, 1)
        val exercisePlan5: ExercisePlan = ExercisePlan(0, 5, 10, 1)

        val list = listOf(exercisePlan1, exercisePlan2, exercisePlan3, exercisePlan4, exercisePlan5)

        list.forEach { daoExercisePlan.insertExerciseProgram(it) }
    }

    suspend fun getPlanOfDay(day: Int) = daoExercisePlan.getExerciseOnPlanFromDay(day)

    suspend fun getAllExercise() = daoExercisePlan.getAllExercise()

    suspend fun writeExercise(exercise: Exercise) = daoExercisePlan.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = daoExercisePlan.updateExercise(exercise)

}