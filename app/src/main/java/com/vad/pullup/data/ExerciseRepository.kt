package com.vad.pullup.data

import com.vad.pullup.data.db.DaoExercisePlan
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.ExercisePlan
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.domain.model.entity.Repeat
import java.sql.Date

class ExerciseRepository(private val daoExercisePlan: DaoExercisePlan) {

    suspend fun setAllProgram(listRepeat: List<Repeat>) {
        listRepeat.forEach {
            daoExercisePlan.insertExerciseProgram(
                ExercisePlan(
                    0,
                    it.count,
                    it.week
                )
            )
        }
    }

    suspend fun getPlanOfWeek(week: Int) = daoExercisePlan.getExerciseOnPlanFromWeek(week)

    suspend fun writeExercise(exercise: Exercise) = daoExercisePlan.insertExercise(exercise)

    suspend fun delete() {
        daoExercisePlan.deleteAll()
    }

    suspend fun getRepeatBetweenDate(from: Date, to: Date) =
        daoExercisePlan.sumGroupByDate()
            .filter { from.time / 1000 <= it.dateRepeat.time / 1000 }
            .filter { it.dateRepeat.time / 1000 <= to.time / 1000 }

    suspend fun getSumRepeatGroupByDate() = daoExercisePlan.sumGroupByDate()

    suspend fun getAllItemProgram(): List<ProgramItem> = daoExercisePlan.getAllProgramItem()

    suspend fun getAllExercise(): List<Exercise> = daoExercisePlan.getAllExercise()

    suspend fun deleteExerciseByDate(date: Long) = daoExercisePlan.deleteByDate(date)

}