package com.crossborders.studyhard


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {



    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Hoş geldin User_name yazdırma
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if(user!=null){
            val Username = user.email
            println("$Username")
            val metin = "Hoş geldin $Username"
            val welcomeUserText : TextView = findViewById(R.id.textView)
            welcomeUserText.setText(/* text = */ metin)
        }


        val fontResourceId = R.font.open_sans_regular
        findViewById<TextView>(R.id.textView).typeface = ResourcesCompat.getFont(this, fontResourceId)
        findViewById<TextView>(R.id.textView).setTextSize(TypedValue.COMPLEX_UNIT_SP , 50.toFloat())

        val playfair_italic= R.font.playfair_italic
        findViewById<TextView>(R.id.gunun_sozu).typeface = ResourcesCompat.getFont(this, playfair_italic)
        findViewById<TextView>(R.id.textView).setTextSize(TypedValue.COMPLEX_UNIT_DIP , 30.toFloat())

    }


}