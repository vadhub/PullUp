package com.vad.pullup.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.vad.pullup.R
import com.vad.pullup.ui.adapter.ViewPagerAdapter

class StatisticFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById(R.id.tabLayout) as TabLayout
        val viewPager2 = view.findViewById(R.id.viewPager) as ViewPager2
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        viewPager2.adapter = viewPagerAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("stat", "selected")
                viewPager2.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("stat", "unselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("stat", "reselected")
            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
    }
}