package com.vad.pullup.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vad.pullup.ui.StatisticChartFragment
import com.vad.pullup.ui.StatisticDetailFragment

class ViewPagerAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StatisticChartFragment()
            1 -> StatisticDetailFragment()
            else -> StatisticChartFragment()
        }
    }
}