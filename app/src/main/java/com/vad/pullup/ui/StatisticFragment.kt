package com.vad.pullup.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.vad.pullup.R
import com.vad.pullup.data.BaseFragment

class StatisticFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chart = view.findViewById(R.id.chart) as LineChart

        exerciseViewModel.getSumRepeat()

        exerciseViewModel.sumRepeat.observe(viewLifecycleOwner) { repeat ->
            Log.d("$1", repeat.toTypedArray().contentToString())

            val dates = repeat.map { "${it.dateRepeat}" }
            val xAxis = chart.xAxis
            xAxis.granularity = 1f
            xAxis.valueFormatter = IndexAxisValueFormatter(dates)

            val x = 1..repeat.size
            val data = x.zip(repeat).map { Entry(it.first.toFloat(), it.second.countRepeat.toFloat()) }

            val dataSet = LineDataSet(data, "Statistic")
            val lineData = LineData(dataSet)

            chart.data = lineData

            chart.invalidate()
        }
    }

}