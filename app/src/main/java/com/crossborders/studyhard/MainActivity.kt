package com.crossborders.studyhard

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Hoş geldin User_name yazdırma
        val currentUser = auth.currentUser
        val username = currentUser?.email?.split("@")?.get(0) ?: "Guest"
        findViewById<TextView>(R.id.textView).apply {
            text = "Hoş geldin $username"
            typeface = ResourcesCompat.getFont(context, R.font.libre_franklin_semibold)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 27.toFloat())
        }

        val fontResourceId = R.font.montserrat_extra_bold
        val views = arrayOf(R.id.gunTextView, R.id.saatTextView, R.id.dakikaTextView)
        views.forEach {
            findViewById<TextView>(it).apply {
                typeface = ResourcesCompat.getFont(context, fontResourceId)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50.toFloat())
            }
        }

        // Geri sayım
        val targetDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse("2024-06-08 10:00:00")!!.time
        val currentTime = System.currentTimeMillis()
        val timeDifference = targetDate - currentTime
        object : CountDownTimer(timeDifference, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val days = millisUntilFinished / (24 * 60 * 60 * 1000)
                val hours = (millisUntilFinished % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
                val minutes = (millisUntilFinished % (60 * 60 * 1000)) / (60 * 1000)

                val countdownViews = arrayOf(R.id.gunTextView, R.id.saatTextView, R.id.dakikaTextView)
                val countdownValues = arrayOf(days, hours, minutes)

                countdownViews.forEachIndexed { index, viewId ->
                    val textView = findViewById<TextView>(viewId)
                    val value = String.format(Locale.getDefault(), "%02d", countdownValues[index])
                    textView.text = value
                }
            }

            override fun onFinish() {
                // Implement if needed
            }
        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ana_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cikis_yap -> {
                auth.signOut()
                val intent = Intent(this, Login_Screen::class.java)
                    .apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onImageButtonClicked(view: View) {
        val intent = when (view.id) {
            R.id.deneme_hesapla -> Intent(this, deneme_net_hesapla::class.java)
            R.id.level -> Intent(this, bunu_unutma::class.java)
            R.id.gecmis_denemeler -> Intent(this, gecmis_denemeler::class.java)
            R.id.performansim -> Intent(this, performansim_page::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }
}
