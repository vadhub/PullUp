package com.vad.pullup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.vad.pullup.R
import com.vad.pullup.BaseFragment
import java.sql.Date
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

        chart.description.isEnabled = false

        val yAxis = chart.axisLeft
        yAxis.axisMinimum = 20f
        yAxis.textSize = 16f

        val simpleDateFormat = SimpleDateFormat("MM.dd")

        chart.axisRight.setDrawLabels(false)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.spaceMin = 0.5f
        xAxis.spaceMax = 0.5f
        xAxis.textSize = 16f

        xAxis.valueFormatter = object : ValueFormatter() {

            override fun getFormattedValue(value: Float): String {
                return simpleDateFormat.format(Date(value.toLong()))
            }
        }

        exerciseViewModel.getSumRepeat()

        exerciseViewModel.sumRepeat.observe(viewLifecycleOwner) { repeat ->
            Log.d("$1", repeat.toTypedArray().contentToString())

            val dates = repeat.map { it.dateRepeat }
            val data = dates.zip(repeat).map { Entry(it.first.time.toFloat(), it.second.countRepeat.toFloat()) }

            val dataSet = LineDataSet(data, "Sum all repeat")

            dataSet.valueTextSize = 16f
            dataSet.setDrawFilled(true)
            dataSet.valueFormatter = DefaultAxisValueFormatter(0)
            val lineData = LineData(dataSet)

            chart.data = lineData

            chart.invalidate()
        }
    }

}