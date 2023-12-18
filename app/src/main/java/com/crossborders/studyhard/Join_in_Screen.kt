package com.crossborders.studyhard


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Join_in_Screen : AppCompatActivity() {


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

