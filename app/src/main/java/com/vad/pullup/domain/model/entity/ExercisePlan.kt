package com.vad.pullup.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_plan")
data class ExercisePlan(

    @PrimaryKey
    @ColumnInfo(name = "id_exercise")
    val idExercise: Int,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "week")
    val week: Int
) {
    companion object {
        val prepopulate = listOf(
            ExercisePlan(idExercise = 1, count = 6, week = 1),
            ExercisePlan(idExercise = 2, count = 5, week = 1),
            ExercisePlan(idExercise = 3, count = 5, week = 1),
            ExercisePlan(idExercise = 4, count = 4, week = 1),
            ExercisePlan(idExercise = 5, count = 3, week = 1),
            ExercisePlan(idExercise = 6, count = 7, week = 2),
            ExercisePlan(idExercise = 7, count = 6, week = 2),
            ExercisePlan(idExercise = 8, count = 5, week = 2),
            ExercisePlan(idExercise = 9, count = 4, week = 2),
            ExercisePlan(idExercise = 10, count = 4, week = 2),
            ExercisePlan(idExercise = 11, count = 8, week = 3),
            ExercisePlan(idExercise = 12, count = 6, week = 3),
            ExercisePlan(idExercise = 13, count = 5, week = 3),
            ExercisePlan(idExercise = 14, count = 5, week = 3),
            ExercisePlan(idExercise = 15, count = 4, week = 3),
            ExercisePlan(idExercise = 16, count = 8, week = 4),
            ExercisePlan(idExercise = 17, count = 7, week = 4),
            ExercisePlan(idExercise = 18, count = 5, week = 4),
            ExercisePlan(idExercise = 19, count = 5, week = 4),
            ExercisePlan(idExercise = 20, count = 5, week = 4),
            ExercisePlan(idExercise = 21, count = 9, week = 5),
            ExercisePlan(idExercise = 22, count = 7, week = 5),
            ExercisePlan(idExercise = 23, count = 6, week = 5),
            ExercisePlan(idExercise = 24, count = 5, week = 5),
            ExercisePlan(idExercise = 25, count = 5, week = 5),
            ExercisePlan(idExercise = 26, count = 10, week = 6),
            ExercisePlan(idExercise = 27, count = 7, week = 6),
            ExercisePlan(idExercise = 28, count = 6, week = 6),
            ExercisePlan(idExercise = 29, count = 6, week = 6),
            ExercisePlan(idExercise = 30, count = 5, week = 6),
            ExercisePlan(idExercise = 31, count = 10, week = 7),
            ExercisePlan(idExercise = 32, count = 8, week = 7),
            ExercisePlan(idExercise = 33, count = 6, week = 7),
            ExercisePlan(idExercise = 34, count = 6, week = 7),
            ExercisePlan(idExercise = 35, count = 6, week = 7),
            ExercisePlan(idExercise = 36, count = 11, week = 8),
            ExercisePlan(idExercise = 37, count = 8, week = 8),
            ExercisePlan(idExercise = 38, count = 7, week = 8),
            ExercisePlan(idExercise = 39, count = 6, week = 8),
            ExercisePlan(idExercise = 40, count = 6, week = 8),
            ExercisePlan(idExercise = 41, count = 12, week = 9),
            ExercisePlan(idExercise = 42, count = 8, week = 9),
            ExercisePlan(idExercise = 43, count = 7, week = 9),
            ExercisePlan(idExercise = 44, count = 7, week = 9),
            ExercisePlan(idExercise = 45, count = 6, week = 9),
            ExercisePlan(idExercise = 46, count = 12, week = 10),
            ExercisePlan(idExercise = 47, count = 9, week = 10),
            ExercisePlan(idExercise = 48, count = 7, week = 10),
            ExercisePlan(idExercise = 49, count = 7, week = 10),
            ExercisePlan(idExercise = 50, count = 7, week = 10),
            ExercisePlan(idExercise = 51, count = 13, week = 11),
            ExercisePlan(idExercise = 52, count = 9, week = 11),
            ExercisePlan(idExercise = 53, count = 8, week = 11),
            ExercisePlan(idExercise = 54, count = 7, week = 11),
            ExercisePlan(idExercise = 55, count = 7, week = 11),
            ExercisePlan(idExercise = 56, count = 14, week = 12),
            ExercisePlan(idExercise = 57, count = 9, week = 12),
            ExercisePlan(idExercise = 58, count = 8, week = 12),
            ExercisePlan(idExercise = 59, count = 8, week = 12),
            ExercisePlan(idExercise = 60, count = 7, week = 12),
            ExercisePlan(idExercise = 61, count = 14, week = 13),
            ExercisePlan(idExercise = 62, count = 10, week = 13),
            ExercisePlan(idExercise = 63, count = 8, week = 13),
            ExercisePlan(idExercise = 64, count = 8, week = 13),
            ExercisePlan(idExercise = 65, count = 8, week = 13),
            ExercisePlan(idExercise = 66, count = 15, week = 14),
            ExercisePlan(idExercise = 67, count = 10, week = 14),
            ExercisePlan(idExercise = 68, count = 9, week = 14),
            ExercisePlan(idExercise = 69, count = 8, week = 14),
            ExercisePlan(idExercise = 70, count = 8, week = 14),
            ExercisePlan(idExercise = 71, count = 16, week = 15),
            ExercisePlan(idExercise = 72, count = 10, week = 15),
            ExercisePlan(idExercise = 73, count = 9, week = 15),
            ExercisePlan(idExercise = 74, count = 9, week = 15),
            ExercisePlan(idExercise = 75, count = 8, week = 15),
            ExercisePlan(idExercise = 76, count = 16, week = 16),
            ExercisePlan(idExercise = 77, count = 11, week = 16),
            ExercisePlan(idExercise = 78, count = 9, week = 16),
            ExercisePlan(idExercise = 79, count = 9, week = 16),
            ExercisePlan(idExercise = 80, count = 9, week = 16),
            ExercisePlan(idExercise = 81, count = 17, week = 17),
            ExercisePlan(idExercise = 82, count = 11, week = 17),
            ExercisePlan(idExercise = 83, count = 10, week = 17),
            ExercisePlan(idExercise = 84, count = 9, week = 17),
            ExercisePlan(idExercise = 85, count = 9, week = 17),
            ExercisePlan(idExercise = 86, count = 18, week = 18),
            ExercisePlan(idExercise = 87, count = 11, week = 18),
            ExercisePlan(idExercise = 88, count = 10, week = 18),
            ExercisePlan(idExercise = 89, count = 10, week = 18),
            ExercisePlan(idExercise = 90, count = 9, week = 18),
            ExercisePlan(idExercise = 91, count = 18, week = 19),
            ExercisePlan(idExercise = 92, count = 12, week = 19),
            ExercisePlan(idExercise = 93, count = 10, week = 19),
            ExercisePlan(idExercise = 94, count = 10, week = 19),
            ExercisePlan(idExercise = 95, count = 10, week = 19),
            ExercisePlan(idExercise = 96, count = 19, week = 20),
            ExercisePlan(idExercise = 97, count = 12, week = 20),
            ExercisePlan(idExercise = 98, count = 11, week = 20),
            ExercisePlan(idExercise = 99, count = 10, week = 20),
            ExercisePlan(idExercise = 100, count = 10, week = 20),
            ExercisePlan(idExercise = 101, count = 20, week = 21),
            ExercisePlan(idExercise = 102, count = 12, week = 21),
            ExercisePlan(idExercise = 103, count = 11, week = 21),
            ExercisePlan(idExercise = 104, count = 11, week = 21),
            ExercisePlan(idExercise = 105, count = 10, week = 21),
            ExercisePlan(idExercise = 106, count = 20, week = 22),
            ExercisePlan(idExercise = 107, count = 13, week = 22),
            ExercisePlan(idExercise = 108, count = 11, week = 22),
            ExercisePlan(idExercise = 109, count = 11, week = 22),
            ExercisePlan(idExercise = 110, count = 11, week = 22),
            ExercisePlan(idExercise = 111, count = 21, week = 23),
            ExercisePlan(idExercise = 112, count = 13, week = 23),
            ExercisePlan(idExercise = 113, count = 12, week = 23),
            ExercisePlan(idExercise = 114, count = 11, week = 23),
            ExercisePlan(idExercise = 115, count = 11, week = 23),
            ExercisePlan(idExercise = 116, count = 22, week = 24),
            ExercisePlan(idExercise = 117, count = 13, week = 24),
            ExercisePlan(idExercise = 118, count = 12, week = 24),
            ExercisePlan(idExercise = 119, count = 12, week = 24),
            ExercisePlan(idExercise = 120, count = 11, week = 24),
            ExercisePlan(idExercise = 121, count = 22, week = 25),
            ExercisePlan(idExercise = 122, count = 14, week = 25),
            ExercisePlan(idExercise = 123, count = 12, week = 25),
            ExercisePlan(idExercise = 124, count = 12, week = 25),
            ExercisePlan(idExercise = 125, count = 12, week = 25),
            ExercisePlan(idExercise = 126, count = 23, week = 26),
            ExercisePlan(idExercise = 127, count = 14, week = 26),
            ExercisePlan(idExercise = 128, count = 13, week = 26),
            ExercisePlan(idExercise = 129, count = 12, week = 26),
            ExercisePlan(idExercise = 130, count = 12, week = 26),
            ExercisePlan(idExercise = 131, count = 24, week = 27),
            ExercisePlan(idExercise = 132, count = 14, week = 27),
            ExercisePlan(idExercise = 133, count = 13, week = 27),
            ExercisePlan(idExercise = 134, count = 13, week = 27),
            ExercisePlan(idExercise = 135, count = 12, week = 27),
            ExercisePlan(idExercise = 136, count = 24, week = 28),
            ExercisePlan(idExercise = 137, count = 15, week = 28),
            ExercisePlan(idExercise = 138, count = 13, week = 28),
            ExercisePlan(idExercise = 139, count = 13, week = 28),
            ExercisePlan(idExercise = 140, count = 13, week = 28),
            ExercisePlan(idExercise = 141, count = 25, week = 29),
            ExercisePlan(idExercise = 142, count = 15, week = 29),
            ExercisePlan(idExercise = 143, count = 14, week = 29),
            ExercisePlan(idExercise = 144, count = 13, week = 29),
            ExercisePlan(idExercise = 145, count = 13, week = 29),
            ExercisePlan(idExercise = 146, count = 26, week = 30),
            ExercisePlan(idExercise = 147, count = 15, week = 30),
            ExercisePlan(idExercise = 148, count = 14, week = 30),
            ExercisePlan(idExercise = 149, count = 14, week = 30),
            ExercisePlan(idExercise = 150, count = 13, week = 30)
        )
    }
}
