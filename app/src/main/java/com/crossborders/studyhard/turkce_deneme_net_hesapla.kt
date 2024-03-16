package com.crossborders.studyhard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View // Eklenen import
import android.widget.AdapterView // Eklenen import
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class turkce_deneme_net_hesapla : AppCompatActivity() {
    private lateinit var turkishCorrectSpinner: Spinner
    private lateinit var turkishWrongSpinner: Spinner
    private lateinit var turkishEmptySpinner: Spinner
    private lateinit var turkishNetEditText: EditText

    private var selectedItems: Int = 0 // Değişkenin dışarıda tanımlanması

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turkce_deneme_net_hesapla)

        turkishCorrectSpinner = findViewById(R.id.turkishCorrectSpinner)
        turkishWrongSpinner = findViewById(R.id.turkishWrongSpinner)
        turkishEmptySpinner = findViewById(R.id.turkishEmptySpinner)
        turkishNetEditText = findViewById(R.id.turkishNet)

        initializeTurkishCorrectSpinner()
        initializeTurkishWrongSpinner()
        initializeTurkishEmptySpinner()
        initializeEditTexts()
        makeSpinnersClickable()

        turkishCorrectSpinner.onItemSelectedListener = createItemSelectedListener()
        turkishWrongSpinner.onItemSelectedListener = createItemSelectedListener()
        turkishEmptySpinner.onItemSelectedListener = createItemSelectedListener()
    }

    private fun initializeSpinner(spinner: Spinner) {
        val items = (0..40).toList().reversed().map { it.toString() }.toTypedArray()
        val adapter = ArrayAdapter(this, R.layout.activity_turkce_deneme_net_hesapla, items)
        adapter.setDropDownViewResource(R.layout.activity_turkce_deneme_net_hesapla)
        spinner.adapter = adapter
    }

    private fun initializeTurkishCorrectSpinner() {
        initializeSpinner(turkishCorrectSpinner)
    }

    private fun initializeTurkishWrongSpinner() {
        initializeSpinner(turkishWrongSpinner)
    }

    private fun initializeTurkishEmptySpinner() {
        initializeSpinner(turkishEmptySpinner)
    }

    private fun initializeEditTexts() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Giriş öncesi
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Giriş esnasında
            }

            override fun afterTextChanged(s: Editable?) {
                // Giriş sonrasında, neti güncelle
                updateTurkishNet()
            }
        }

        turkishNetEditText.addTextChangedListener(textWatcher)
    }

    private fun makeSpinnersClickable() {
        turkishCorrectSpinner.setOnTouchListener { _, _ ->
            turkishCorrectSpinner.performClick()
            true
        }

        turkishWrongSpinner.setOnTouchListener { _, _ ->
            turkishWrongSpinner.performClick()
            true
        }

        turkishEmptySpinner.setOnTouchListener { _, _ ->
            turkishEmptySpinner.performClick()
            true
        }
    }


    private fun createItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItems = selectedItems.toInt()
                updateTurkishNet()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde
            }
        }
    }

    private fun updateTurkishNet() {
        val correct = turkishCorrectSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val wrong = turkishWrongSpinner.selectedItem.toString().toIntOrNull() ?: 0
        val empty = turkishEmptySpinner.selectedItem.toString().toIntOrNull() ?: 0

        var net = correct - ((wrong / 4) - empty - wrong)
        val maxNet: Int = if (turkishCorrectSpinner.selectedItemPosition == 0) {
            0
        } else {
            40
        }

        // Eğer net 40'tan büyükse veya seçilen değerlerin toplamı 40'tan büyükse hata mesajı göster
        if (net > maxNet || correct + wrong + empty > 40) {
            net = 0
            Toast.makeText(this, "Hata: Net değeri maksimum değerden büyük!", Toast.LENGTH_SHORT).show()
        }

        turkishNetEditText.setText(net.toString())
    }
}
