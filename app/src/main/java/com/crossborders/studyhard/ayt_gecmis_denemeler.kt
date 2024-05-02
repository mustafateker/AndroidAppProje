package com.crossborders.studyhard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crossborders.studyhard.databinding.FragmentAytGecmisDenemelerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ayt_gecmis_denemeler : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding : FragmentAytGecmisDenemelerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var denemeAdapter: DenemeAdapter
    private var denemeList = mutableListOf<Deneme>()
    private lateinit var mReferance : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAytGecmisDenemelerBinding.inflate(inflater,container,false)
        val view = binding.root

        recyclerView = view.findViewById(R.id.ayt_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        denemeAdapter = DenemeAdapter(denemeList)
        recyclerView.adapter = denemeAdapter

        val currentUserId = FirebaseAuth.getInstance().uid.toString()
        mReferance = FirebaseDatabase.getInstance().getReference("users/$currentUserId/user_denemeler/user_tytdenemeleri")

        mReferance.addValueEventListener(object : ValueEventListener {
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




        // Inflate the layout for this fragment
        return view
    }

}