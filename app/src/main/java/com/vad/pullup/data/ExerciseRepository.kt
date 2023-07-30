package com.vad.pullup.data

import com.vad.pullup.data.db.DaoExercisePlan
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun setAllProgram(listRepeat: List<Repeat>) {
        listRepeat.forEach { daoExercisePlan.insertExerciseProgram(ExercisePlan(0, it.count, it.week)) }
    }

    suspend fun getAllProgram() = daoExercisePlan.getAllProgram()

    suspend fun getPlanOfWeek(week: Int) = daoExercisePlan.getExerciseOnPlanFromWeek(week)

    suspend fun getAllExercise() = daoExercisePlan.getAllExercise()

    suspend fun writeExercise(exercise: Exercise) = daoExercisePlan.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = daoExercisePlan.updateExercise(exercise)

    suspend fun delete() {
        daoExercisePlan.deleteAll()
    }

    suspend fun getSumRepeatGroupByDate() = daoExercisePlan.sumGroupByDate()

}