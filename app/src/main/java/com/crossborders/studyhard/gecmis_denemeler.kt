package com.crossborders.studyhard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class gecmis_denemeler : AppCompatActivity() {
        private lateinit var DenemeAdapter: DenemeAdapter
        private val denemeList = mutableListOf<Deneme>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gecmis_denemeler)

        DenemeAdapter = DenemeAdapter(denemeList)
        val recyclerView : RecyclerView = findViewById(R.id.main_activity_recycler_view)
        recyclerView.adapter = DenemeAdapter


        val yeniDeneme = Deneme(1 , "ÜçDörtBeşTYT" , "02.03.2024" , 85,0)
        denemeList.add(yeniDeneme)
        DenemeAdapter.notifyDataSetChanged()

    }
}