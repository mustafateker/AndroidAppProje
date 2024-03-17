package com.crossborders.studyhard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class gecmis_denemeler : AppCompatActivity() {
    private lateinit var denemeAdapter: DenemeAdapter
    private val denemeList = mutableListOf<Deneme>()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gecmis_denemeler)

        // RecyclerView ve adapter'ı ayarlayın
        val recyclerView: RecyclerView = findViewById(R.id.main_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        denemeAdapter = DenemeAdapter(denemeList)
        recyclerView.adapter = denemeAdapter

        // Firebase veritabanı referansı
        database = FirebaseDatabase.getInstance().getReference("denemeler")

        // Firebase'den verileri çek ve RecyclerView'a ekle
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Temizleme işlemi
                denemeList.clear()

                // Her veri öğesi için döngü
                for (ds in snapshot.children) {
                    try {
                        // Firebase'den veriyi al
                        val denemeNo = ds.child("denemeNo").getValue(Double::class.java)?.toInt() ?: 0
                        val denemeAdi = ds.child("denemeAdi").getValue(String::class.java) ?: ""
                        val denemeTarihiMillis = ds.child("denemeTarihi").getValue(Long::class.java) ?: 0
                        val netSayisi = ds.child("netSayisi").getValue(Double::class.java)?.toInt() ?: 0

                        // Unix zaman damgasını tarihe dönüştür
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val denemeTarihi = formatter.format(Date(denemeTarihiMillis))

                        // Deneme nesnesi oluştur ve listeye ekle
                        val deneme = Deneme(denemeNo, denemeAdi, denemeTarihi, netSayisi)
                        denemeList.add(deneme)
                    } catch (e: Exception) {
                        Log.e("Firebase", "Veri işleme hatası: ${e.message}")
                    }
                }
                // Değişiklikleri adapter'a bildir
                denemeAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata durumunda işlemler burada yapılabilir
                // Örneğin: Loglama
                Log.e("Firebase", "Veri okuma hatası: ${error.message}")
                Log.e("Firebase", "Details: ${error.details}")
                Log.e("Firebase", "Code: ${error.code}")
                // Diğer özellikler de eklenerek hata detayları görüntülenebilir
            }
        })
    }
}
