package com.crossborders.studyhard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.crossborders.studyhard.databinding.ActivityPerformansimPageBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class PerformansimPage : AppCompatActivity() {

    private lateinit var binding: ActivityPerformansimPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerformansimPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Performansim başlığı font belirliyor
        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.performansim_title).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)

        // Grafik oluşturma
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

        binding.PerformansimBarChart.animateY(1500)
        binding.PerformansimBarChart.setFitBars(true)
        binding.PerformansimBarChart.data = barData
        binding.PerformansimBarChart.description.text = "Haftalık Performans"
        binding.PerformansimBarChart.xAxis.valueFormatter = IndexAxisValueFormatter(weeklyFollow)
        binding.PerformansimBarChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

}
