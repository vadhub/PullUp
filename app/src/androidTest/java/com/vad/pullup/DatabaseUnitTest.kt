package com.vad.pullup

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vad.pullup.data.db.AppDatabase
import com.vad.pullup.data.db.DaoExercisePlan
import com.vad.pullup.domain.model.entity.Exercise
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.sql.Date

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class DatabaseUnitTest {

    private lateinit var dao: DaoExercisePlan
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        dao = db.exerciseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertExerciseAndReadList() = runBlocking {
        val exercise: Exercise = Exercise(1, 2, 23, Date(System.currentTimeMillis()))
        dao.insertExercise(exercise)

        val result = dao.getAllExercise()

        Assert.assertEquals(exercise, result[0])
    }

    @Test
    @Throws(IOException::class)
    fun deleteByDate() = runBlocking {
        val exercise1: Exercise = Exercise(1, 2, 23, Date(System.currentTimeMillis()))
        val exercise2: Exercise = Exercise(2, 2, 23, Date(System.currentTimeMillis()))
        val exercise3: Exercise = Exercise(3, 2, 23, Date(System.currentTimeMillis()))
        val exercise4: Exercise = Exercise(4, 2, 23, Date(1691936889597))

        dao.insertExercise(exercise1)
        dao.insertExercise(exercise2)
        dao.insertExercise(exercise3)
        dao.insertExercise(exercise4)

        dao.deleteByDate(1691936889597)

        val result = dao.getAllExercise()

        println(result)

        Assert.assertEquals(exercise1, result[0])
    }
}