package com.vad.pullup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vad.pullup.data.Configuration
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.domain.model.entity.Repeat
import com.vad.pullup.ui.ExerciseViewModel
import com.vad.pullup.ui.ExerciseViewModelFactory
import com.vad.pullup.ui.ViewModelUIConfig
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var configuration: Configuration
    private lateinit var exerciseViewModel: ExerciseViewModel
    val visibleNavBar: ViewModelUIConfig by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        configuration = Configuration(this)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_pull_up)
        bottomMenu.selectedItemId = R.id.trainFragment

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        bottomMenu.setupWithNavController(navController)

        val factory = ExerciseViewModelFactory(ExerciseRepository((application as App).database.exerciseDao()))
        exerciseViewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)

        Log.d("firstStart", "${configuration.getFirstStart()}")

        if (savedInstanceState == null) {
            Log.d("d", "!!!!!!!!!!")
            if (configuration.getFirstStart()) {
                exerciseViewModel.deleteAllProgram()
                exerciseViewModel.setProgram(readCSVProgram())
                Log.d("##1", "firsttt")
                configuration.saveFirstStart(false)
                navController.popBackStack()
                navController.navigate(R.id.preparationFragment)
            }
        }

        visibleNavBar.visibleNavBar.observe(this) {
            if(it) {
                bottomMenu.visibility = View.VISIBLE
            } else {
                bottomMenu.visibility = View.GONE
            }
        }

    }

    private fun readCSVProgram(): List<Repeat> {
        val bufferReader = BufferedReader(assets.open("traini.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )

        val listRepeat = mutableListOf<Repeat>()
        csvParser.drop(1).forEach {
            val repeat = Repeat(
                week = it.get(0).toInt(),
                count = it.get(1).toInt()
            )

            listRepeat.add(repeat)
        }

        return listRepeat
    }

}