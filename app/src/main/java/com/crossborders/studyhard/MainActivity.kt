package com.crossborders.studyhard


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        // Hoş geldin User_name yazdırma
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            val Username = user.email?.split("@")?.get(0) ?: "Guest"
            val metin = "Hoş geldin $Username"
            val welcomeUserText: TextView = findViewById(R.id.textView)
            welcomeUserText.text = metin
        }

        val fontResourceId = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.textView).typeface = ResourcesCompat.getFont(this, fontResourceId)
        findViewById<TextView>(R.id.textView).setTextSize(TypedValue.COMPLEX_UNIT_SP, 27.toFloat())

        /*val playfairItalic = R.font.open_sans_regular
        findViewById<TextView>(R.id.gunun_sozu).typeface = ResourcesCompat.getFont(this, playfairItalic)
        findViewById<TextView>(R.id.gunun_sozu).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25.toFloat())*/

        val codepro = R.font.montserrat_extra_bold
        findViewById<TextView>(R.id.gunTextView).typeface = ResourcesCompat.getFont(this , codepro)
        findViewById<TextView>(R.id.gunTextView).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.toFloat())

        findViewById<TextView>(R.id.saatTextView).typeface = ResourcesCompat.getFont(this , codepro)
        findViewById<TextView>(R.id.saatTextView).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.toFloat())

        findViewById<TextView>(R.id.dakikaTextView).typeface = ResourcesCompat.getFont(this , codepro)
        findViewById<TextView>(R.id.dakikaTextView).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.toFloat())


        // Geri sayım
        val geriSayimGunText: TextView = findViewById(R.id.gunTextView)
        val geriSayimSaatText: TextView = findViewById(R.id.saatTextView)
        val geriSayimDakikaText: TextView = findViewById(R.id.dakikaTextView)

        val hedefTarih = "2024-06-08 10:00:00"
        val tarihFormati = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val hedefTarihZamani = tarihFormati.parse(hedefTarih)
        val anlikTarih = System.currentTimeMillis()

        val zamanFarki = hedefTarihZamani!!.time - anlikTarih
        val start = object : CountDownTimer(zamanFarki, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val kalanZaman = millisUntilFinished
                val gun = kalanZaman / (24 * 60 * 60 * 1000)
                val saat = (kalanZaman % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
                val dakika = (kalanZaman % (60 * 60 * 1000)) / (60 * 1000)

                val geriSayimGunTextView = String.format(Locale.getDefault(), "%02d", gun)
                val geriSayimSaatTextView = String.format(Locale.getDefault(), "%02d", saat)
                val geriSayimDakikaTextView = String.format(Locale.getDefault(), "%02d", dakika)

                geriSayimGunText.text = geriSayimGunTextView
                geriSayimSaatText.text = geriSayimSaatTextView
                geriSayimDakikaText.text = geriSayimDakikaTextView
            }


            override fun onFinish() {
             }
        }.start()


        val denemeNetHesapla : ImageButton =findViewById(R.id.deneme_hesapla)
        denemeNetHesapla.setOnClickListener(){
            val denemeNetHesaplaPage = Intent(applicationContext,deneme_net_hesapla::class.java)
            startActivity(denemeNetHesaplaPage)
        }
        val bunu_unutma_page : ImageButton= findViewById(R.id.level)
        bunu_unutma_page.setOnClickListener(){
            val bunu_unutmaPage = Intent(applicationContext,bunu_unutma::class.java)
            startActivity(bunu_unutmaPage)
        }

        val gecmisDenemeler : ImageButton = findViewById(R.id.gecmis_denemeler)
        gecmisDenemeler.setOnClickListener() {
            val gecmisDenemelerPage = Intent(applicationContext, gecmis_denemeler::class.java)
            startActivity(gecmisDenemelerPage)
        }




    }


}