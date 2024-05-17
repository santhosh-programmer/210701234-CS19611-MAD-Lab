package com.example.lab01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var changeFontButton: Button
    private lateinit var changeColorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        changeFontButton = findViewById(R.id.changeFontButton)
        changeColorButton = findViewById(R.id.changeColorButton)

        changeFontButton.setOnClickListener {
            // Change font here
            // For example:
            textView.setTypeface(null, Typeface.BOLD)
            showToast("Font changed")
        }

        changeColorButton.setOnClickListener {
            // Change color here
            // For example:
            textView.setTextColor(Color.BLUE)
            // Change background color
            textView.setBackgroundColor(Color.YELLOW)
            showToast("Color and background color changed")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}