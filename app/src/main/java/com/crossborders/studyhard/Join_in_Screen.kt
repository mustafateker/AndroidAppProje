package com.crossborders.studyhard



import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class Join_in_Screen : AppCompatActivity() {
    private lateinit var mReferance : DatabaseReference
    private fun validatePasswords() : Boolean{
        val textPassword : EditText = findViewById(R.id.Parola1)
        val textPassword_2 : EditText = findViewById(R.id.ParolaTekrar)


        val parola = textPassword.text.toString()
        val confirmparola= textPassword_2.text.toString()

        return if(parola != confirmparola){
            Toast.makeText(
                this@Join_in_Screen,
                "Parola ve Parola Tekrar aynı olmalıdır!",
                Toast.LENGTH_SHORT
            ).show()
            false
        }else {
            true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_in_screen)

        val registerButton: Button = findViewById(/* id = */ R.id.JoininButton)
        val EmailText: EditText = findViewById(R.id.UsernameText)
        val Parola1: EditText = findViewById(R.id.Parola1)
        val ParolaTekrar: EditText = findViewById(R.id.ParolaTekrar)
        val ad : EditText = findViewById(R.id.ad)
        val soyad : EditText = findViewById(R.id.soyad)
        val nickname : EditText = findViewById(R.id.nickname)

        mReferance = FirebaseDatabase.getInstance().getReference("users")


        /*fun hashSHA256(data: String): String {
            val messageDigest = MessageDigest.getInstance("SHA-256)")
            val hashBytes = messageDigest.digest(data.toByteArray())

            val hexStringBuilder = StringBuilder()
            for (byte in hashBytes) {
                val hex = String.format("%02x", byte)
                hexStringBuilder.append(hex)
            }
            return hexStringBuilder.toString()
        }*/

        registerButton.setOnClickListener {

                validatePasswords()

                when {

                TextUtils.isEmpty(ad.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Join_in_Screen,
                        "Lütfen ad giriniz",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(soyad.text.toString().trim { it <= ' ' })-> {
                    Toast.makeText(
                        this@Join_in_Screen,
                        "Lütfen Soyad giriniz",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                    TextUtils.isEmpty(nickname.text.toString().trim {it <= ' '}) -> {
                        Toast.makeText(
                            this@Join_in_Screen,
                            "Lütfen kullanıcı adı giriniz",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                TextUtils.isEmpty(EmailText.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Join_in_Screen,
                        "Lütfen email adresinizi giriniz.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(Parola1.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Join_in_Screen,
                        "Lütfen Parola Giriniz.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(ParolaTekrar.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Join_in_Screen,
                        "Lütfen Parolayı Tekrar Giriniz.",
                        Toast.LENGTH_SHORT
                    ).show()
                }



                /*TextUtils.equals(Parola1.text.toString(), ParolaTekrar.text.toString().trim { it <= ' ' }) ->{
                        Toast.makeText(
                            this@Join_in_Screen,
                            "Parola ve Parola Tekrar aynı olmalıdır!",
                            Toast.LENGTH_SHORT
                        ).show()
                }*/


                    else -> {

                    val email: String = EmailText.text.toString().trim() { it <= ' ' }
                    val password: String = Parola1.text.toString().trim() { it <= ' ' }
                    val ad : String = ad.text.toString().trim(){it <= ' '}
                    val soyad : String = soyad.text.toString().trim(){it <= ' '}
                    val nickname : String = nickname.text.toString().trim(){it <= ' '}
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            { task ->

                                //kaydolma başarılıysa
                                if (task.isSuccessful) {
                                    //Firebase kayıtlı kullanıcı
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@Join_in_Screen,
                                        "Kaydolma Başarılı.",
                                        Toast.LENGTH_SHORT
                                    ).show()




                                    val intent =
                                        Intent(this@Join_in_Screen, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email.id", email)

                                    val user_id = firebaseUser.uid
                                    val newUser = hashMapOf(
                                        "user_id" to user_id,
                                        "user_nickname" to nickname,
                                        "user_soyadi" to soyad,
                                        "user_email" to email,
                                        "user_adi" to  ad
                                    )

                                    val user_info = "user_info"
                                    mReferance.child(user_id.toString()).child(user_info).setValue(newUser)

                                    val getdata = object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            var sb = StringBuilder()
                                            for (i in snapshot.children) {
                                                val ad = i.child("user_adi").getValue(String::class.java)
                                                val soyad = i.child("user_soyadi").getValue(String::class.java)
                                                val user_id = i.child("user_id").getValue(String::class.java)
                                                val user_email = i.child("user_email").getValue(String::class.java)
                                                val nickname = i.child("nickname").getValue(String::class.java)
                                                sb.append("${i.key} - $ad - $soyad - $user_id - $user_email - $nickname\n")
                                            }

                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            // Hata durumunda işlemler burada yapılabilir
                                        }
                                    }

                                    mReferance.addValueEventListener(getdata)
                                    mReferance.addListenerForSingleValueEvent(getdata)

                                    startActivity(intent)
                                    finish()
                            } else {

                                    //Kaydolma başarısız olmuşsa bir hata mesajı gösterecez

                                    Toast.makeText(
                                        this@Join_in_Screen,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                             }
                        }
                    )
                }
            }


        }
    }
}

