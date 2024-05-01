package com.crossborders.studyhard
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crossborders.studyhard.databinding.ActivityDenemeEkle2Binding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
//FirebaseAuth.getInstance().currentUser.uid //lazım olacaka user id ile verilere erişme
class DenemeEkle : AppCompatActivity() {
    private lateinit var binding: ActivityDenemeEkle2Binding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDenemeEkle2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val current_user_id = FirebaseAuth.getInstance().uid.toString()
        val denemeler = "user_denemeler"
        database = FirebaseDatabase.getInstance().getReference("users").child(current_user_id).child(denemeler)

        binding.butonEkleId.setOnClickListener {
            val denemeNo = binding.denemeNoId.text.toString().toIntOrNull() ?: 0
            val denemeAdi = binding.denemeAdiId.text.toString()
            val tarihInput = binding.denemeTarihiId.text.toString()

            // Kullanıcı tarafından girilen tarihin doğru formatta olup olmadığını kontrol et
            if (isValidDate(tarihInput)) {
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val denemeTarihi = dateFormat.parse(tarihInput)?.time ?: Date().time
                val netSayisi = binding.netSayisiId.text.toString().toDoubleOrNull() ?: 0.0

                val yeniDeneme = hashMapOf(
                    "denemeNo" to denemeNo,
                    "denemeAdi" to denemeAdi,
                    "denemeTarihi" to denemeTarihi,
                    "netSayisi" to netSayisi
                )
                database.child(denemeNo.toString()).setValue(yeniDeneme)
            } else {
                // Geçersiz tarih girişi olduğunda kullanıcıya uyarı mesajı göster
                showAlert("Geçersiz Tarih", "Lütfen doğru bir tarih formatı giriniz (gg/aa/yyyy).")
            }
        }

        val getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sb = StringBuilder()
                for (i in snapshot.children) {
                    val adSoyad = i.child("denemeAdi").getValue(String::class.java)
                    val net = i.child("netSayisi").getValue(Double::class.java)
                    val tarihLong = i.child("denemeTarihi").getValue(Long::class.java)
                    val tarih = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(tarihLong ?: 0)

                    sb.append("${i.key} - $adSoyad - $net - $tarih\n")
                }
                binding.tvsonucId.text = sb.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Hata durumunda işlemler burada yapılabilir
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }

    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false // Geçersiz tarihleri kabul etmemek için lenient modunu kapat
        return try {
            dateFormat.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Tamam") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}


