package com.vad.pullup.data

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun getPlanOfDay(day: Int) = daoExercisePlan.getExerciseOnPlanFromDay(day)

    suspend fun getAllExercise() = daoExercisePlan.getAllExercise()

    suspend fun writeExercise(exercise: Exercise) = daoExercisePlan.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = daoExercisePlan.updateExercise(exercise)

}