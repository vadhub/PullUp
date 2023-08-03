package com.vad.pullup.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vad.pullup.data.entity.Exercise
import com.vad.pullup.data.entity.ExercisePlan
import com.vad.pullup.data.entity.ProgramItem

@Database(entities = [ExercisePlan::class, Exercise::class, ProgramItem::class], version = 3, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): DaoExercisePlan

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }

    }

}