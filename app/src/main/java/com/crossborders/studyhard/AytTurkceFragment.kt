package com.crossborders.studyhard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.crossborders.studyhard.databinding.AytTurkceFragmentBinding


class AytTurkceFragment : Fragment() {
    private lateinit var binding: AytTurkceFragmentBinding

    //ayt cografya 1variables
    private lateinit var cografya1CorrectSpinner : Spinner
    private lateinit var cografya1WrongSpinner : Spinner
    private lateinit var cografya1EmptyTextView : TextView
    private lateinit var cografya1NetTextView : TextView

    //ayt tarih 1variables
    private lateinit var tarih1CorrectSpinner : Spinner
    private lateinit var tarih1WrongSpinner: Spinner
    private lateinit var tarih1EmptyTextView: TextView
    private lateinit var tarih1NetTextView: TextView

    //ayt türkçe variables
    private lateinit var turkdiliCorrectSpinner: Spinner
    private lateinit var turkdiliWrongSpinner: Spinner
    private lateinit var turkdiliEmptyTextView: TextView
    private lateinit var turkdiliNetTextView: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AytTurkceFragmentBinding.inflate(inflater , container, false)
        val view = binding.root

        cografya1CorrectSpinner = view.findViewById(R.id.cografya1CorrectSpinner)
        cografya1WrongSpinner = view.findViewById(R.id.cografya1WrongSpinner)
        cografya1EmptyTextView = view.findViewById(R.id.cografya1Empty)
        cografya1NetTextView = view.findViewById(R.id.cografya1Net)

        tarih1CorrectSpinner = view.findViewById(R.id.tarih1CorrectSpinner)
        tarih1WrongSpinner = view.findViewById(R.id.tarih1WrongSpinner)
        tarih1EmptyTextView = view.findViewById(R.id.tarih1Empty)
        tarih1NetTextView = view.findViewById(R.id.tarih1Net)

        turkdiliCorrectSpinner = view.findViewById(R.id.aytturkdiliCorrectSpinner)
        turkdiliWrongSpinner = view.findViewById(R.id.aytturkdiliWrongSpinner)
        turkdiliEmptyTextView = view.findViewById(R.id.aytturkdiliEmpty)
        turkdiliNetTextView = view.findViewById(R.id.aytturkdiliNet)



        return view
    }



}