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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Weekly_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Weekly_Fragment : Fragment() {
    private lateinit var binding: FragmentWeeklyBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Weekly_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Weekly_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}