package com.crossborders.studyhard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crossborders.studyhard.databinding.FragmentWeeklyBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import javax.net.ssl.SSLSessionBindingEvent

class WeeklyFragment : Fragment() {
    private lateinit var binding: FragmentWeeklyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        val view = binding.root

        val barEntry = arrayListOf<BarEntry>()
        val weeklyFollow = arrayOf("Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar")

        barEntry.add(BarEntry(0f, 85.00f))
        barEntry.add(BarEntry(1f, 90.00f))
        barEntry.add(BarEntry(2f, 86.25f))
        barEntry.add(BarEntry(3f, 78.00f))
        barEntry.add(BarEntry(4f, 92.00f))
        barEntry.add(BarEntry(5f, 71.50f))
        barEntry.add(BarEntry(6f, 64.50f))

        val barDataSet = BarDataSet( barEntry, "Haftalık Performans")
        barDataSet.valueTextSize = 15f
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val barData = BarData(barDataSet)

        binding.weeklyBarChart.animateY(1500)
        binding.weeklyBarChart.setFitBars(true)
        binding.weeklyBarChart.data = barData
        binding.weeklyBarChart.description.text = "Haftalık Performans"
        binding.weeklyBarChart.xAxis.valueFormatter = IndexAxisValueFormatter(weeklyFollow)
        binding.weeklyBarChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        return view
    }


    }

