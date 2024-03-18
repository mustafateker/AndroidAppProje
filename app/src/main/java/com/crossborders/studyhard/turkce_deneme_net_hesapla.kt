package com.crossborders.studyhard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View // Eklenen import
import android.widget.AdapterView // Eklenen import
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton



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


    private lateinit var hesaplaButton: AppCompatButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turkce_deneme_net_hesapla)

        turkishCorrectSpinner = findViewById(R.id.turkishCorrectSpinner)
        turkishWrongSpinner = findViewById(R.id.turkishWrongSpinner)
        turkishNetTextView = findViewById(R.id.turkishNet)
        turkishemptyTextView = findViewById(R.id.turkishEmpty)

        //matematik id define
        matematikCorrectSpinner = findViewById(R.id.matematikCorrectSpinner)
        matematikWrongSpinner = findViewById(R.id.matematikWrongSpinner)
        matematikemptyTextView = findViewById(R.id.matematikEmpty)
        matematikNetTextView = findViewById(R.id.matematikNet)


        hesaplaButton = findViewById(R.id.HesaplaButton)


        initializeTurkishCorrectSpinner()
        initializeTurkishWrongSpinner()

        // matematik initialize



        initializeTextView()
        turkishCorrectSpinner.isClickable = true
        turkishWrongSpinner.isClickable = true

        // matematikclick
        matematikCorrectSpinner.isClickable = true
        matematikWrongSpinner.isClickable = true


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




        hesaplaButton.setOnClickListener() {
            updateNet()
        }
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
        turkishemptyTextView.addTextChangedListener(textWatcher)
        turkishNetTextView.addTextChangedListener(textWatcher)

        matematikemptyTextView.addTextChangedListener(textWatcher)
        matematikNetTextView.addTextChangedListener(textWatcher)

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
    }

    private fun updateNet() {
        val correct = turkishCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrong = turkishWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0

        val correctMatematik = matematikCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrongMatematik = matematikWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0



        val net: Number = when {
            correct == 0 && wrong == 0 -> 0 // Hiç doğru veya yanlış cevap yoksa net 0'dır
            correct == 40 -> 40 // Tüm sorular doğru cevaplandıysa net 40'tır

            correctMatematik == 0 && wrongMatematik == 0 -> 0
            correctMatematik == 40 -> 40

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

        val currentTextMatematik = matematikNetTextView.text.toString()
        if(currentTextMatematik != netMatematik.toString() ){
            matematikNetTextView.setText((netMatematik.toString()))
        }
        val currentText = turkishNetTextView.text.toString()
        if (currentText != net.toString()) {
            turkishNetTextView.setText(net.toString())
        }
    }
    //Matematikkkkkk privateee funn /////////////////
}



