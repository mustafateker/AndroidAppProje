package com.crossborders.studyhard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class deneme_net_hesapla : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme_net_hesapla)

        val TytDenemeHesapla : ImageButton = findViewById(R.id.tyt_deneme_net_hesapla_button)
        TytDenemeHesapla.setOnClickListener(){
            val tytDenemeHesaplaPage = Intent(applicationContext, turkce_deneme_net_hesapla::class.java)
            startActivity(tytDenemeHesaplaPage)
        }

        val AytDenemeHesapla : ImageButton = findViewById(R.id.imageButton2)
        AytDenemeHesapla.setOnClickListener(){
            val aytDenemeHesaplaPage = Intent(applicationContext, AytDenemeHesaplaActivity::class.java)
            startActivity(aytDenemeHesaplaPage)
        }
    }

}