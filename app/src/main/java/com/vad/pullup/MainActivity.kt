package com.vad.pullup

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vad.pullup.data.Configuration
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.entity.Repeat
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager : SensorManager? = null
    private var lastUpdate: Long = 0
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

//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//
//        sensorManager?.registerListener(this,
//            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//            SensorManager.SENSOR_DELAY_NORMAL);
//        lastUpdate=System.currentTimeMillis();

    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this);
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

    override fun onSensorChanged(event: SensorEvent?) {
//        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
//            val values = event.values
//
//            val x = values[0]
//            val y = values[1]
//            val z = values[2]
//
//            val accelationSquareRoot = ((x * x + y * y + z * z)
//                    / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH))
//            val actualTime = System.currentTimeMillis()
//            if (accelationSquareRoot >= 1.5)
//            {
//                if (actualTime - lastUpdate < 100) {
//                    return
//                }
//                lastUpdate = actualTime
//                view.text = "${counter++}"
//                if (color) {
//                    view.setBackgroundColor(Color.GREEN)
//                } else {
//                    view.setBackgroundColor(Color.RED)
//                }
//                color = !color
//            }
//        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}