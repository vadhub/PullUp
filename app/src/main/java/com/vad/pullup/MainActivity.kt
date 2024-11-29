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
import com.vad.pullup.ui.viewmodel.ExerciseViewModel
import com.vad.pullup.ui.viewmodel.ExerciseViewModelFactory
import com.vad.pullup.ui.viewmodel.ViewModelUIConfig
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var configuration: Configuration

    private val factory by lazy { ExerciseViewModelFactory(ExerciseRepository((application as App).database.exerciseDao())) }
    val exerciseViewModel: ExerciseViewModel by lazy {
        ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)
    }
    val visibleNavBar: ViewModelUIConfig by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mBanner = findViewById<BannerAdView>(R.id.adView)

        mBanner.setAdUnitId("R-M-2613052-1")
        mBanner.setAdSize(getAdSize(mBanner))
        val adRequest: AdRequest = AdRequest.Builder().build()
        mBanner.loadAd(adRequest)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        configuration = Configuration(this)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_pull_up)
        bottomMenu.selectedItemId = R.id.trainFragment

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        bottomMenu.setupWithNavController(navController)

        Log.d("firstStart", "${configuration.getFirstStart()}")

        if (savedInstanceState == null) {
            Log.d("d", "!!!!!!!!!!")
            if (configuration.getFirstStart()) {
                exerciseViewModel.deleteAllProgram()
                Log.d("##1", "firsttt")
                configuration.saveFirstStart(false)
                navGraph.setStartDestination(R.id.preparationFragment)
            } else {
                navGraph.setStartDestination(R.id.trainFragment)
            }
        }

//        exerciseViewModel.insertExercise()

        navController.graph = navGraph

        visibleNavBar.visibleNavBar.observe(this) {
            if (it) {
                bottomMenu.visibility = View.VISIBLE
            } else {
                bottomMenu.visibility = View.GONE
            }
        }

    }

    fun getAdSize(mBannerAdView: BannerAdView): BannerAdSize {
        val displayMetrics = resources.displayMetrics
        // Calculate the width of the ad, taking into account the padding in the ad container.
        var adWidthPixels: Int = mBannerAdView.getWidth()
        if (adWidthPixels == 0) {
            // If the ad hasn't been laid out, default to the full screen width
            adWidthPixels = displayMetrics.widthPixels
        }
        val adWidth = Math.round(adWidthPixels / displayMetrics.density)
        return BannerAdSize.stickySize(this, adWidth)
    }

}