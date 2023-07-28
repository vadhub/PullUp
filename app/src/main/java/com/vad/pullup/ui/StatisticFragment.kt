package com.vad.pullup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.vad.pullup.R
import com.vad.pullup.BaseFragment
import java.text.SimpleDateFormat

class StatisticFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chart = view.findViewById(R.id.chart) as LineChart

        val yAxis = chart.axisLeft
        yAxis.axisMinimum = 20f

        chart.axisRight.textSize = 16f
        yAxis.textSize = 16f
        val simpleDateFormat = SimpleDateFormat("MM.dd")
        exerciseViewModel.getSumRepeat()

        exerciseViewModel.sumRepeat.observe(viewLifecycleOwner) { repeat ->
            Log.d("$1", repeat.toTypedArray().contentToString())

            val dates = repeat.map { simpleDateFormat.format(it.dateRepeat) }
            val xAxis = chart.xAxis
            xAxis.textSize = 16f
            xAxis.granularity = 1f
            xAxis.valueFormatter = IndexAxisValueFormatter(dates)

            val x = 1..repeat.size
            val data = x.zip(repeat).map { Entry(it.first.toFloat(), it.second.countRepeat.toFloat()) }

            val dataSet = LineDataSet(data, "Statistic")
            dataSet.valueTextSize = 16f
            val lineData = LineData(dataSet)

            chart.data = lineData

            chart.invalidate()
        }
    }

}