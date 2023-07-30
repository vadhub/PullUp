package com.vad.pullup.data

import com.vad.pullup.data.db.ExercisePlan

class ConverterProgram {

    companion object {

        fun convertToListProgram(listExercisePlan: List<ExercisePlan>, weeks: Int): List<ProgramItem> {

            val listProgramItem = mutableListOf<ProgramItem>()

            var j = 0
            for (i in 1..weeks) {

                val countArray = mutableListOf<Int>()

                for (k in 1..5) {
                    countArray.add(listExercisePlan[j].count)
                    j++
                }

                listProgramItem.add(
                    ProgramItem(
                        i,
                        countArray[0],
                        countArray[1],
                        countArray[2],
                        countArray[3],
                        countArray[4]
                    )
                )
            }

            return listProgramItem
        }
    }
}