package com.crossborders.studyhard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crossborders.studyhard.databinding.AytSosyal2FragmentBinding

class AytSosyal2Fragment : Fragment() {

    private lateinit var binding : AytSosyal2FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AytSosyal2FragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }


}