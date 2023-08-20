package com.vad.pullup.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.ExercisePlan
import com.vad.pullup.domain.model.entity.ProgramItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ExercisePlan::class, Exercise::class, ProgramItem::class],
    version = 4,
    exportSchema = false
)

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
                ).fallbackToDestructiveMigration().addCallback(callback).build()

                INSTANCE = instance
                instance
            }
        }

        private val callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.exerciseDao().let {
                            it?.insertExerciseProgram(ExercisePlan.prepopulate)
                            it?.insertProgramLineItem(ProgramItem.items)
                        }
                }
            }
        }
    }

}