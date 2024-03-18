package com.crossborders.studyhard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ayarlar_button : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayarlar_button)

        val listView: ListView = findViewById<ListView>(R.id.listview)
        val items = arrayOf("Şifre Değiştir" ,"Şifre")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            if (position == 0) { // Eğer ilk öğeye tıklandıysa
                val intent = Intent(this, passwordChange::class.java)
                startActivity(intent)
            } else {
                // Diğer öğelere tıklanırsa başka bir işlem yapılabilir veya bu blok atlanabilir.
            }
        }

    }
}
