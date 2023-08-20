package com.vad.pullup.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program_item")
data class ProgramItem(

    @PrimaryKey
    @ColumnInfo(name = "id_program_item")
    val id: Int,

    @ColumnInfo(name = "week")
    val week: Int,

    @ColumnInfo(name = "first")
    val first: Int,

    @ColumnInfo(name = "second")
    val second: Int,

    @ColumnInfo(name = "third")
    val third: Int,

    @ColumnInfo(name = "fourth")
    val fourth: Int,

    @ColumnInfo(name = "fifth")
    val fifth: Int
) {
    companion object {
        val items = listOf(
            ProgramItem(id = 1, week = 1, first = 6, second = 5, third = 5, fourth = 4, fifth = 3),
            ProgramItem(id = 2, week = 2, first = 7, second = 6, third = 5, fourth = 4, fifth = 4),
            ProgramItem(id = 3, week = 3, first = 8, second = 6, third = 5, fourth = 5, fifth = 4),
            ProgramItem(id = 4, week = 4, first = 8, second = 7, third = 5, fourth = 5, fifth = 5),
            ProgramItem(id = 5, week = 5, first = 9, second = 7, third = 6, fourth = 5, fifth = 5),
            ProgramItem(id = 6, week = 6, first = 10, second = 7, third = 6, fourth = 6, fifth = 5),
            ProgramItem(id = 7, week = 7, first = 10, second = 8, third = 6, fourth = 6, fifth = 6),
            ProgramItem(id = 8, week = 8, first = 11, second = 8, third = 7, fourth = 6, fifth = 6),
            ProgramItem(id = 9, week = 9, first = 12, second = 8, third = 7, fourth = 7, fifth = 6),
            ProgramItem(id = 10, week = 10, first = 12, second = 9, third = 7, fourth = 7, fifth = 7),
            ProgramItem(id = 11, week = 11, first = 13, second = 9, third = 8, fourth = 7, fifth = 7),
            ProgramItem(id = 12, week = 12, first = 14, second = 9, third = 8, fourth = 8, fifth = 7),
            ProgramItem(id = 13, week = 13, first = 14, second = 10, third = 8, fourth = 8, fifth = 8),
            ProgramItem(id = 14, week = 14, first = 15, second = 10, third = 9, fourth = 8, fifth = 8),
            ProgramItem(id = 15, week = 15, first = 16, second = 10, third = 9, fourth = 9, fifth = 8),
            ProgramItem(id = 16, week = 16, first = 16, second = 11, third = 9, fourth = 9, fifth = 9),
            ProgramItem(id = 17, week = 17, first = 17, second = 11, third = 10, fourth = 9, fifth = 9),
            ProgramItem(id = 18, week = 18, first = 18, second = 11, third = 10, fourth = 10, fifth = 9),
            ProgramItem(id = 19, week = 19, first = 18, second = 12, third = 10, fourth = 10, fifth = 10),
            ProgramItem(id = 20, week = 20, first = 19, second = 12, third = 11, fourth = 10, fifth = 10),
            ProgramItem(id = 21, week = 21, first = 20, second = 12, third = 11, fourth = 11, fifth = 10),
            ProgramItem(id = 22, week = 22, first = 20, second = 13, third = 11, fourth = 11, fifth = 11),
            ProgramItem(id = 23, week = 23, first = 21, second = 13, third = 12, fourth = 11, fifth = 11),
            ProgramItem(id = 24, week = 24, first = 22, second = 13, third = 12, fourth = 12, fifth = 11),
            ProgramItem(id = 25, week = 25, first = 22, second = 14, third = 12, fourth = 12, fifth = 12),
            ProgramItem(id = 26, week = 26, first = 23, second = 14, third = 13, fourth = 12, fifth = 12),
            ProgramItem(id = 27, week = 27, first = 24, second = 14, third = 13, fourth = 13, fifth = 12),
            ProgramItem(id = 28, week = 28, first = 24, second = 15, third = 13, fourth = 13, fifth = 13),
            ProgramItem(id = 29, week = 29, first = 25, second = 15, third = 14, fourth = 13, fifth = 13),
            ProgramItem(id = 30, week = 30, first = 26, second = 15, third = 14, fourth = 14, fifth = 13)
        )
    }
}
