package com.vad.pullup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.vad.pullup.BaseFragment
import com.vad.pullup.R
import java.sql.Date
import java.text.SimpleDateFormat

class StatisticChartFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chart_statistic, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val chart = view.findViewById(R.id.chart) as LineChart
        val emptyText = view.findViewById<TextView>(R.id.emptyChartTextView)

        chart.description.isEnabled = false
        chart.setPinchZoom(false)
        chart.legend.textColor = resources.getColor(R.color.teal_200)

        val yAxis = chart.axisLeft
        yAxis.textSize = 12f
        yAxis.textColor = resources.getColor(R.color.teal_200)

        val simpleDateFormat = SimpleDateFormat("MM.dd")

        chart.axisRight.setDrawLabels(false)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isGranularityEnabled = true
        xAxis.granularity = 1f
        xAxis.setLabelCount(6, true)
        xAxis.textSize = 12f
        xAxis.textColor = resources.getColor(R.color.teal_200)

        xAxis.valueFormatter = object : ValueFormatter() {

            override fun getFormattedValue(value: Float): String {
                return simpleDateFormat.format(Date(value.toLong()))
            }
        }

        exerciseViewModel.getSumRepeat()
        exerciseViewModel.sumRepeat.observe(viewLifecycleOwner) { repeat ->
            Log.d("$1", repeat.toTypedArray().contentToString())
            if (repeat.isNotEmpty()) {
                emptyText.visibility = View.GONE
            }
            val dates = repeat.map { it.dateRepeat }
            val data = dates.zip(repeat)
                .map { Entry(it.first.time.toFloat(), it.second.countRepeat.toFloat()) }

            val dataSet = LineDataSet(data, resources.getString(R.string.sum_all_repeat))


            dataSet.valueTextSize = 12f
            dataSet.valueTextColor = resources.getColor(R.color.teal_200)
            dataSet.setDrawFilled(true)
            dataSet.valueFormatter = DefaultAxisValueFormatter(0)
            val lineData = LineData(dataSet)

            chart.data = lineData

            chart.invalidate()
        }
    }

}