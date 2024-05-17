package com.crossborders.studyhard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View // Eklenen import
import android.widget.AdapterView // Eklenen import
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID


class turkce_deneme_net_hesapla: AppCompatActivity() {
    private lateinit var turkishCorrectSpinner: Spinner
    private lateinit var turkishWrongSpinner: Spinner
    private lateinit var turkishemptyTextView: TextView
    private lateinit var turkishNetTextView: TextView
    //matemateik private

    private lateinit var matematikCorrectSpinner: Spinner
    private lateinit var matematikWrongSpinner: Spinner
    private lateinit var matematikemptyTextView: TextView
    private lateinit var matematikNetTextView: TextView

    //sosyal private

    private lateinit var sosyalCorrectSpinner: Spinner
    private lateinit var sosyalWrongSpinner: Spinner
    private lateinit var sosyalemptyTextView: TextView
    private lateinit var sosyalNetTextView: TextView

    //FEN////////////

   private lateinit var fenCorrectSpinner: Spinner
   private lateinit var fenWrongSpinner: Spinner
   private lateinit var fenemptyTextView: TextView
   private lateinit var fenNetTextView: TextView

   //toplamNet

   private lateinit var ToplamNetTextView:TextView

   private lateinit var hesaplaButton: AppCompatButton

   //kayder butonu
   private lateinit var denemeAdi : AppCompatEditText
   private lateinit var kaydetButton : AppCompatButton
   private lateinit var mReferance : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turkce_deneme_net_hesapla)

        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.textView10).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)


        turkishCorrectSpinner = findViewById(R.id.turkishCorrectSpinner)
        turkishWrongSpinner = findViewById(R.id.turkishWrongSpinner)
        turkishNetTextView = findViewById(R.id.turkishNet)
        turkishemptyTextView = findViewById(R.id.turkishEmpty)

        //matematik id define
        matematikCorrectSpinner = findViewById(R.id.matematikCorrectSpinner)
        matematikWrongSpinner = findViewById(R.id.matematikWrongSpinner)
        matematikemptyTextView = findViewById(R.id.matematikEmpty)
        matematikNetTextView = findViewById(R.id.matematikNet)

         // sosyal id define
        sosyalCorrectSpinner=findViewById(R.id.sosyalCorrectSpinner)
        sosyalWrongSpinner=findViewById(R.id.sosyalWrongSpinner)
        sosyalemptyTextView=findViewById(R.id.sosyalEmpty)
        sosyalNetTextView=findViewById(R.id.sosyalNet)

        //Fen id define

        fenCorrectSpinner=findViewById(R.id.FenCorrectSpinner)
        fenWrongSpinner=findViewById(R.id.FenWrongSpinner)
        fenemptyTextView=findViewById(R.id.FenEmpty)
        fenNetTextView=findViewById(R.id.FenNet)

         // toplamNet
        ToplamNetTextView=findViewById(R.id.genelNet)

        hesaplaButton = findViewById(R.id.HesaplaButton)


        initializeTurkishCorrectSpinner()
        initializeTurkishWrongSpinner()
        initializeSosyalCorrectSpinner()
        initializeSosyalWrongSpinner()

        initializeTextView()

        turkishCorrectSpinner.isClickable = true
        turkishWrongSpinner.isClickable = true

        // matematikclick
        matematikCorrectSpinner.isClickable = true
        matematikWrongSpinner.isClickable = true

        //sosyalclick
        sosyalCorrectSpinner.isClickable=true
        sosyalWrongSpinner.isClickable=true

        //fenclick

        fenCorrectSpinner.isClickable=true
        fenWrongSpinner.isClickable=true

        turkishCorrectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }
        }

        turkishWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        // matematikselected

        matematikCorrectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    updateEmpty()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Hiçbir şey seçilmediğinde
                }
            }

        matematikWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }
        }
          //SOSYALLLL SELECTED

        sosyalCorrectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    updateEmpty()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Hiçbir şey seçilmediğinde
                }
            }
                    sosyalWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            updateEmpty()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Hiçbir şey seçilmediğinde
                }
            }
        //Fen selected/////////


        fenCorrectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    updateEmpty()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Hiçbir şey seçilmediğinde
                }
            }
        fenWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }
        }

        hesaplaButton.setOnClickListener() {
            updateNet()
        }

        //kaydetme ve database'e verileri gönderme sekansı

        denemeAdi = findViewById(R.id.tyt_deneme_adi_id)
        kaydetButton = findViewById(R.id.tyt_deneme_kaydet_button)


        val current_user_id = FirebaseAuth.getInstance().uid.toString()
        val denemeler = "user_denemeler"
        val denemeType = "user_tytdenemeleri"

        mReferance = FirebaseDatabase.getInstance().getReference("users").child(current_user_id).child(denemeler).child(denemeType)
        val currentDate = System.currentTimeMillis()
        val denemeId = UUID.randomUUID().toString()

        mReferance.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val denemeNo = datasnapshot.childrenCount + 1


                kaydetButton.setOnClickListener{

                    val getDenemeAdi = denemeAdi.text.toString()
                    val turkishNet = turkishNetTextView.text.toString()
                    val matematikNet = matematikNetTextView.text.toString()
                    val sostalNet = sosyalNetTextView.text.toString()
                    val fenNet = fenNetTextView.text.toString()
                    val genelNet = ToplamNetTextView.text.toString()

                    while (getDenemeAdi.isEmpty() == true){
                        Toast.makeText(
                            this@turkce_deneme_net_hesapla,
                            "Lütfen deneme adi giriniz",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val yeniTytDeneme = hashMapOf(
                        "tytDenemeNo" to denemeNo,
                        "tytDenemeId" to denemeId,
                        "tytDenemeAdi" to getDenemeAdi,
                        "tytDenemeTarihi" to currentDate,
                        "tytTurkishNet" to turkishNet,
                        "tytMatematikNet" to matematikNet,
                        "tytSosyalNet" to sostalNet,
                        "tytFenNet" to fenNet,
                        "tytGenelNet" to genelNet
                    )
                    mReferance.child(denemeId).setValue(yeniTytDeneme)

                    Toast.makeText(
                        this@turkce_deneme_net_hesapla,
                        "Deneme Başarıyla Kaydedildi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Veri okuma hatası: ${error.message}")
                Log.e("Firebase", "Details: ${error.details}")
                Log.e("Firebase", "Code: ${error.code}")
            }
        })



    }

    //turkce private fun/////////////////////
    private fun initializeTurkishCorrectSpinner() {
        val values = (40 downTo 0).toList().map { (40 - it).toString() }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        turkishCorrectSpinner.adapter = adapter

        val adaptermatematik = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        matematikCorrectSpinner.adapter = adaptermatematik





        turkishCorrectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        matematikCorrectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
    private fun initializeSosyalCorrectSpinner() {
        val values = (20 downTo 0).toList().map { (20 - it).toString() }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sosyalCorrectSpinner.adapter = adapter

        val adapterfen= ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fenCorrectSpinner.adapter = adapterfen


        sosyalCorrectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }

        }
        fenCorrectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    private fun initializeSosyalWrongSpinner() {
        val values = (20 downTo 0).toList().map { (20 - it).toString() }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sosyalWrongSpinner.adapter = adapter

        val adapterfen = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fenWrongSpinner.adapter = adapterfen

        sosyalWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }

        }
        fenWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun initializeTurkishWrongSpinner() {
        val values = (40 downTo 0).toList().map { (40 - it).toString() }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        turkishWrongSpinner.adapter = adapter

        val adaptermatematik = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        matematikWrongSpinner.adapter = adaptermatematik



        turkishWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        matematikWrongSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    //matematik ////////////


    private fun initializeTextView() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Giriş öncesi
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Giriş esnasında

            }

            override fun afterTextChanged(s: Editable?) {
                // Giriş sonrasında, neti güncelle

            }
        }

        sosyalemptyTextView.addTextChangedListener(textWatcher)
        sosyalNetTextView.addTextChangedListener(textWatcher)

        turkishemptyTextView.addTextChangedListener(textWatcher)
        turkishNetTextView.addTextChangedListener(textWatcher)

        matematikemptyTextView.addTextChangedListener(textWatcher)
        matematikNetTextView.addTextChangedListener(textWatcher)

        fenemptyTextView.addTextChangedListener(textWatcher)
        fenNetTextView.addTextChangedListener(textWatcher)


    }


    private fun updateEmpty() {
        val correct = turkishCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrong = turkishWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val empty = 40 - (correct + wrong)
        turkishemptyTextView.text = empty.toString()

        val correctMatematik = matematikCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongMatematik = matematikWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val emptyMatematik = 40 - (correctMatematik + wrongMatematik)
        matematikemptyTextView.text = emptyMatematik.toString()

        val correctsosyal = sosyalCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongsosyal = sosyalWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val emptysosyal = 20 - (correctsosyal + wrongsosyal)
        sosyalemptyTextView.text = emptysosyal.toString()

        val correctfen = fenCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongfen = fenWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val emptyfen = 20 - (correctfen+ wrongfen)
        fenemptyTextView.text = emptyfen.toString()
    }




    private fun updateNet() {
        val correct = turkishCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrong = turkishWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0

        val correctMatematik = matematikCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongMatematik = matematikWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0

        val correctsosyal = sosyalCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongsosyal = sosyalWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0

        val correctfen = fenCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongfen = fenWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0

        val net: Number = when {

            correct == 0 && wrong == 0 -> 0 // Hiç doğru veya yanlış cevap yoksa net 0'dır
            correct == 40 -> 40 // Tüm sorular doğru cevaplandıysa net 40'tır

            else -> {
                val wrongPenaltyTurkce = (wrong * 0.25).toFloat()
                val totalCorrectTurkce = correct - wrongPenaltyTurkce
                val netScoreTurkce = totalCorrectTurkce
                netScoreTurkce
            }
        }

        val netMatematik: Number = when {

            correctMatematik == 0 && wrongMatematik == 0 -> 0
            correctMatematik == 40 -> 40

            else -> {
                val wrongPenaltyMatematik = (wrongMatematik * 0.25).toFloat()
                val totalCorrectMatematik = correctMatematik - wrongPenaltyMatematik
                val netScoreMatematik = totalCorrectMatematik
                netScoreMatematik
            }
        }

        val netsosyal: Number = when {

            correctsosyal == 0 && wrongsosyal == 0 -> 0
            correctsosyal == 20 -> 20

            else -> {
                val wrongPenaltysosyal = (wrongsosyal * 0.25).toFloat()
                val totalCorrectsosyal = correctsosyal - wrongPenaltysosyal
                val netScoresosyal = totalCorrectsosyal
                netScoresosyal
            }
        }

        val netfen: Number = when {

            correctfen == 0 && wrongfen== 0 -> 0
            correctfen == 20 -> 20

            else -> {
                val wrongPenaltyfen = (wrongfen * 0.25).toFloat()
                val totalCorrectfen = correctfen - wrongPenaltyfen
                val netScorefen = totalCorrectfen
                netScorefen
            }
        }
        val ToplamNet : Number = (net.toFloat()+ netMatematik.toFloat() + netfen.toFloat() + netsosyal.toFloat())


        val currentTextMatematik = matematikNetTextView.text.toString()
        if(currentTextMatematik != netMatematik.toString() ){
            matematikNetTextView.setText((netMatematik.toString()))
        }
        val currentText = turkishNetTextView.text.toString()
        if (currentText != net.toString()) {
            turkishNetTextView.setText(net.toString())
        }
        val currentTextsosyal = sosyalNetTextView.text.toString()
        if (currentTextsosyal != netsosyal.toString()) {
            sosyalNetTextView.setText(netsosyal.toString())
        }
        val currentTextfen = fenNetTextView.text.toString()
        if (currentTextfen != netfen.toString()) {
            fenNetTextView.setText(netfen.toString())
        }
        val currentTextToplamNet = ToplamNetTextView.text.toString()
        if (currentTextToplamNet != ToplamNet.toString()) {
            ToplamNetTextView.setText(ToplamNet.toString())
        }
    }

}



