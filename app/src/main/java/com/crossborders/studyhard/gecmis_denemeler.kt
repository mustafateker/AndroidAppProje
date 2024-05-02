@file:Suppress("DEPRECATION")

package com.crossborders.studyhard

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.crossborders.studyhard.databinding.FragmentTytGecmisDenemelerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class gecmis_denemeler : AppCompatActivity() {
    /*private lateinit var denemeAdapter: DenemeAdapter
    private val denemeList = mutableListOf<Deneme>()
    private lateinit var database: DatabaseReference*/
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gecmis_denemeler)

        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.gecmis_denemeler_title).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)

        viewPager = findViewById(R.id.gecmis_denemeler_view_pager)
        setupViewPager()

        /*// RecyclerView ve adapter'ı ayarlayın
        val recyclerView: RecyclerView = findViewById(R.id.main_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        denemeAdapter = DenemeAdapter(denemeList)
        recyclerView.adapter = denemeAdapter

        val currentUserId =FirebaseAuth.getInstance().uid.toString()
        // Firebase veritabanı referansı
        database = FirebaseDatabase.getInstance().getReference("users/$currentUserId/user_denemeler/user_tytdenemeleri")

        // Firebase'den verileri çek ve RecyclerView'a ekle
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Temizleme işlemi
                denemeList.clear()

                // Her veri öğesi için döngü
                for (ds in snapshot.children) {
                    try {
                        // Firebase'den veriyi al
                        val denemeNo = ds.child("user_tyt_deneme_no").getValue(Double::class.java)?.toInt() ?: 0
                        val denemeAdi = ds.child("tyt_denemeAdi").getValue(String::class.java) ?: ""
                        val denemeTarihiMillis = ds.child("tyt_deneme_tarihi").getValue(Long::class.java) ?: 0
                        val netSayisi = ds.child("tyt_genelNet").getValue(Double::class.java)?.toInt() ?: 0

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

         */
    }
    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(tyt_gecmis_denemeler(), "TYT")
        adapter.addFragment(ayt_gecmis_denemeler(), "AYT")
        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

}

