package com.vad.pullup

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.vad.pullup.data.Configuration
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.domain.model.entity.Repeat
import com.vad.pullup.ui.ChooseProgramFragment
import com.vad.pullup.ui.PreparationFragment
import com.vad.pullup.ui.StatisticFragment
import com.vad.pullup.ui.TrainFragment
import com.vad.pullup.ui.viewmodel.ExerciseViewModel
import com.vad.pullup.ui.viewmodel.ExerciseViewModelFactory
import com.vad.pullup.ui.viewmodel.ViewModelUIConfig
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var configuration: Configuration

    private val factory by lazy { ExerciseViewModelFactory(ExerciseRepository((application as App).database.exerciseDao())) }
    val exerciseViewModel: ExerciseViewModel by lazy {
        ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)
    }
    val visibleNavBar: ViewModelUIConfig by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        configuration = Configuration(this)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_pull_up)
        bottomMenu.selectedItemId = R.id.trainFragment

        bottomMenu.setOnItemSelectedListener(this)

        Log.d("firstStart", "${configuration.getFirstStart()}")

        if (savedInstanceState == null) {
            var fragment: Fragment = TrainFragment()
            Log.d("d", "!!!!!!!!!!")
            if (configuration.getFirstStart()) {
                exerciseViewModel.deleteAllProgram()
                exerciseViewModel.setProgram(readCSVProgram())
                Log.d("##1", "firsttt")
                configuration.saveFirstStart(false)
                fragment = PreparationFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        }

        visibleNavBar.visibleNavBar.observe(this) {
            if (it) {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment = when(item.itemId) {
            R.id.statisticFragment -> StatisticFragment()
            R.id.trainFragment -> TrainFragment()
            R.id.chooseProgramFragment -> ChooseProgramFragment()
            else -> StatisticFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit()
        return true
    }

}