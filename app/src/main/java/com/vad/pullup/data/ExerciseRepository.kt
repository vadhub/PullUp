package com.vad.pullup.data

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun getPlanOfDay(day: Int) =
        daoExercisePlan.getExerciseOnPlanFromDay(day)


    fun getAllExercise() {

    }

    fun writeExercise() {

    }

    fun updateExercise() {

    }


}