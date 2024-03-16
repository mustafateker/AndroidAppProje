package com.crossborders.studyhard

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.crossborders.studyhard.databinding.FragmentMonthlyBinding
import com.crossborders.studyhard.databinding.FragmentWeeklyBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.time.LocalDate
import java.util.Calendar
import java.util.*

class MonthlyFragment : Fragment() {
    private lateinit var binding: FragmentMonthlyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMonthlyBinding.inflate(inflater , container , false)
        val view = binding.root

        val barEntry = arrayListOf<BarEntry>()

        val currentDate = Calendar.getInstance()
        val thirtyDaysAgo = Calendar.getInstance()
        thirtyDaysAgo.add(Calendar.DAY_OF_MONTH, -30)

        val monthlyTracking = ArrayList<Calendar>()

        var dateToAdd = thirtyDaysAgo.clone() as Calendar
        var dayCounter = 0
        while (dateToAdd.before(currentDate) || dateToAdd == currentDate) {
            monthlyTracking.add(dateToAdd.clone() as Calendar)
            dateToAdd.add(Calendar.DAY_OF_MONTH, 1)
            dayCounter++
        }

        barEntry.add(BarEntry(0f,58f))
        barEntry.add(BarEntry(1f,58f))
        barEntry.add(BarEntry(2f,58f))
        barEntry.add(BarEntry(3f,58f))
        barEntry.add(BarEntry(4f,58f))
        barEntry.add(BarEntry(5f,58f))
        barEntry.add(BarEntry(6f,58f))
        barEntry.add(BarEntry(7f,58f))
        barEntry.add(BarEntry(8f,58f))
        barEntry.add(BarEntry(9f,58f))

        val barDataSet = BarDataSet( barEntry, "Aylık Performans")
        barDataSet.valueTextSize = 15f
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val barData = BarData(barDataSet)

        binding.monthlyBarChart.animateY(1500)
        binding.monthlyBarChart.setFitBars(true)
        binding.monthlyBarChart.data = barData
        binding.monthlyBarChart.description.text = "Aylık Performans"
        binding.monthlyBarChart.xAxis.position = XAxis.XAxisPosition.BOTTOM



        return view
    }


}