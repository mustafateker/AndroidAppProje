package com.crossborders.studyhard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Login_Screen : AppCompatActivity() {

    private lateinit var mReferance : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)




        val hemenKaydolLink : TextView = findViewById(R.id.hemenKaydolLink)

        hemenKaydolLink.setOnClickListener() {
            val JoinInPage = Intent(applicationContext,Join_in_Screen::class.java)
            startActivity(JoinInPage)


        }

        val LoginBtn : Button = findViewById(R.id.LoginButton)
        val EmailLoginText : EditText = findViewById(R.id.UsernameText)
        val PasswordText : EditText = findViewById(R.id.Parola1)


        val benihatirla : CheckBox = findViewById(R.id.BeniHatirla)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (benihatirla.isClickable) {
            editor.putBoolean("rememberMe", true)
            editor.putString("username", "kullaniciAdi")
            editor.putString("password", "sifre")
        } else {
            editor.clear()
        }


        editor.apply()

        LoginBtn.setOnClickListener{
            when{
                TextUtils.isEmpty(EmailLoginText.text.toString().trim{ it <= ' '})->
                {
                    Toast.makeText(
                        this@Login_Screen,
                        "Lütfen mail adresinizi giriniz.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(PasswordText.text.toString().trim { it <= ' ' }) ->
                {
                    Toast.makeText(
                        this@Login_Screen,
                        "Lütfen parola giriniz.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val emailorusername: String = EmailLoginText.text.toString().trim(){it <= ' '}
                    val password : String = PasswordText.text.toString().trim(){it <= ' '}


                    mReferance = FirebaseDatabase.getInstance().getReference("users")
                    val query = mReferance.orderByChild("user_info/user_nickname").equalTo(emailorusername)
                        .limitToFirst(1)

                    if(containsAtSymbol(emailorusername)) {
                        Authentication(emailorusername,password)

                    }else{
                        query.addValueEventListener(object : ValueEventListener {

                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists())
                                {
                                    val userEmail = snapshot.children.first().child("user_info/user_email")
                                        .getValue(String::class.java).toString()
                                    Authentication(userEmail,password)
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.e("Firebase", "Veri okuma hatası: ${error.message}")
                                Log.e("Firebase", "Details: ${error.details}")
                                Log.e("Firebase", "Code: ${error.code}")
                            }
                        })
                    }
                }
            }

        }


    }

    private fun Authentication(email:String , password:String){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                { task ->
                    //Giriş yapıldıysa
                    if (task.isSuccessful) {

                        val firebaseUser: FirebaseUser? =
                            task.result?.user

                        if (firebaseUser != null) {
                            Toast.makeText(
                                this@Login_Screen,
                                "Giriş başarılı",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(
                                    this@Login_Screen,
                                    MainActivity::class.java
                                )
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@Login_Screen,
                                "FirebaseUser null hatası",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {

                        Toast.makeText(
                            this@Login_Screen,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })
    }
    private fun containsAtSymbol(input: String): Boolean {
        return '@' in input
    }
}