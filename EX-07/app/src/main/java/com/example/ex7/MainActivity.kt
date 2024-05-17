package com.example.ex7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextID = findViewById<EditText>(R.id.editTextID)
        val buttonValidate = findViewById<Button>(R.id.buttonValidate)

        buttonValidate.setOnClickListener {
            val username = editTextUsername.text.toString()
            val idText = editTextID.text.toString()

            if (username.isEmpty() || idText.isEmpty()) {
                Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!username.matches(Regex("[a-zA-Z]+"))) {
                Toast.makeText(this, "Username should only contain alphabets", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = idText.toIntOrNull()

            if (id == null || id < 1000 || id > 9999) {
                Toast.makeText(this, "ID should be a 4-digit number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("username", username)
            intent.putExtra("id", id)
            startActivity(intent)

        }
    }
}
