package com.vad.pullup.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program_item")
data class ProgramItem(

    @PrimaryKey(autoGenerate = true)
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
}