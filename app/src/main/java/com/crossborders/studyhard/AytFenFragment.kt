package com.crossborders.studyhard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crossborders.studyhard.databinding.AytFenFragmentBinding

class AytFenFragment : Fragment() {
    private lateinit var binding : AytFenFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AytFenFragmentBinding.inflate(inflater,container  ,false)
        val view = binding.root



        return view
    }


}