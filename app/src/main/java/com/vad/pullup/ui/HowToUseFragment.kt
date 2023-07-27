package com.vad.pullup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.vad.pullup.R
import com.vad.pullup.data.BaseFragment

class HowToUseFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_how_to_use, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = view.findViewById(R.id.viewPager) as ViewPager2
        val adapter = ViewPagerAdapter()
        viewPager.adapter = adapter

    }
}