package com.example.tugas2_emptyviewactivity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTemperature: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    private val temperatureUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTemperature = findViewById(R.id.editTextTemperature)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatureUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        buttonConvert.setOnClickListener { convertTemperature() }
    }

    private fun convertTemperature() {
        val temperatureInput = editTextTemperature.text.toString()
        if (temperatureInput.isNotEmpty()) {
            val fromUnit = spinnerFrom.selectedItem.toString()
            val toUnit = spinnerTo.selectedItem.toString()
            val temperatureValue = temperatureInput.toDouble()
            val convertedValue = when (fromUnit) {
                "Celsius" -> when (toUnit) {
                    "Fahrenheit" -> (temperatureValue * 9/5) + 32
                    "Kelvin" -> temperatureValue + 273.15
                    else -> temperatureValue
                }
                "Fahrenheit" -> when (toUnit) {
                    "Celsius" -> (temperatureValue - 32) * 5/9
                    "Kelvin" -> (temperatureValue - 32) * 5/9 + 273.15
                    else -> temperatureValue
                }
                "Kelvin" -> when (toUnit) {
                    "Celsius" -> temperatureValue - 273.15
                    "Fahrenheit" -> (temperatureValue - 273.15) * 9/5 + 32
                    else -> temperatureValue
                }
                else -> temperatureValue
            }
            textViewResult.text = String.format("%.2f", convertedValue)
        } else {
            textViewResult.text = "Masukkan suhu yang valid."
        }
    }
}
