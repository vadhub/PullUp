package com.vad.pullup

import com.vad.pullup.domain.model.ConverterProgram
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.domain.model.entity.ExercisePlan
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun convert_isCorrect() {
        val listProgram = mutableListOf(
            ProgramItem(0, 1, 30, 25, 20, 15, 10),
            ProgramItem(0, 2, 30, 25, 20, 15, 10),
            ProgramItem(0, 3, 30, 25, 20, 15, 10)
        )

        val listExercisePlan = mutableListOf(
            ExercisePlan(0, 30, 1), ExercisePlan(0, 25, 1),
            ExercisePlan(0, 20, 1),
            ExercisePlan(0, 15, 1),
            ExercisePlan(0, 10, 1),
            ExercisePlan(0, 30, 2), ExercisePlan(0, 25, 2),
            ExercisePlan(0, 20, 2),
            ExercisePlan(0, 15, 2),
            ExercisePlan(0, 10, 2),
            ExercisePlan(0, 30, 3), ExercisePlan(0, 25, 3),
            ExercisePlan(0, 20, 3),
            ExercisePlan(0, 15, 3),
            ExercisePlan(0, 10, 3)
        )


        val listConverted = ConverterProgram.convertToListProgram(listExercisePlan, 3)
        assertEquals(listProgram, listConverted)

       print(listConverted.toTypedArray().contentToString())

    }
}