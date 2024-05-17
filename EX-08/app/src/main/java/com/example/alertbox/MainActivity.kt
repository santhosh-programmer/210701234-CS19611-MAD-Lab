package com.example.alertbox

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertButton: Button = findViewById(R.id.alertButton)

        alertButton.setOnClickListener {
            displayAlert()
        }
    }

    private fun displayAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is an alert message!")
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when OK button is clicked
        }
        builder.show()
    }
}


