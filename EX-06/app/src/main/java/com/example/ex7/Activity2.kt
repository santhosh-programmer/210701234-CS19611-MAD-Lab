package com.example.ex7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        val username = intent.getStringExtra("username")
        val id = intent.getIntExtra("id", 0)


        val result = "Username: $username\nID: $id"

        textViewResult.text = result
    }
}
