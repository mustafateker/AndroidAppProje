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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Performansim başlığı font belirliyor
        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.performansim_title).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)

    }
}


