package com.crossborders.studyhard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crossborders.studyhard.databinding.FragmentTytGecmisDenemelerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class tyt_gecmis_denemeler : Fragment() {
    private lateinit var binding: FragmentTytGecmisDenemelerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var denemeAdapter : DenemeAdapter
    private val denemeList = mutableListOf<Deneme>()
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTytGecmisDenemelerBinding.inflate(inflater,container, false)
        val view = binding.root

        recyclerView = view.findViewById(R.id.main_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        denemeAdapter = DenemeAdapter(denemeList)
        recyclerView.adapter = denemeAdapter

        val currentUserId = FirebaseAuth.getInstance().uid
        databaseReference = FirebaseDatabase.getInstance().getReference("users/$currentUserId/user_denemeler/user_tytdenemeleri")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Temizleme işlemi
                denemeList.clear()

                // Her bir deneme ID'si için
                for (denemeSnapshot in snapshot.children) {
                    try {
                        // Deneme verilerini direkt olarak al
                        val denemeNo = denemeSnapshot.child("user_tyt_deneme_no").getValue(Int::class.java)?.toInt() ?: 0
                        val denemeAdi = denemeSnapshot.child("tyt_denemeAdi").getValue(String::class.java) ?: ""
                        val denemeTarihiMillis = denemeSnapshot.child("tyt_deneme_tarihi").getValue(Long::class.java) ?: 0
                        val netSayisi = denemeSnapshot.child("tyt_genelNet").getValue(String::class.java)?.toInt() ?: 0

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
                Log.e("Firebase", "Veri okuma hatası: ${error.message}")
            }
        })

        return view
    }




}