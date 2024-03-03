package com.crossborders.studyhard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class gecmis_denemeler : AppCompatActivity() {
    private lateinit var denemeAdapter: DenemeAdapter
    private val denemeList = mutableListOf<Deneme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gecmis_denemeler)

        // Veri ekleyin
        val yeniDeneme = Deneme(1, "ÜçDörtBeşTYT", "02.03.2024", 85)
        denemeList.add(yeniDeneme)

        // RecyclerView ve adapter'ı ayarlayın
        val recyclerView: RecyclerView = findViewById(R.id.main_activity_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        denemeAdapter = DenemeAdapter(denemeList)
        recyclerView.adapter = denemeAdapter
    }
}
