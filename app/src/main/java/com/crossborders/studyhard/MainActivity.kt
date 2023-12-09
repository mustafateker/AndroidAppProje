@file:Suppress("NAME_SHADOWING")

package com.crossborders.studyhard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crossborders.studyhard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val JoinInPage=binding.root
        setContentView(JoinInPage)

        binding.hemenKaydolLink.setOnClickListener() {
            val JoinInPage = Intent(applicationContext,Join_in_Screen::class.java)
            startActivity(JoinInPage)
        }



    }


}