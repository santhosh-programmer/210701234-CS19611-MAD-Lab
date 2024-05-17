package com.example.sendsms
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)
        val editTextMessage = findViewById<EditText>(R.id.editTextMessage)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val phoneNumber = editTextPhoneNumber.text.toString()
            val message = editTextMessage.text.toString()

            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                sendSMS(phoneNumber, message)
            } else {
                // Request permission to send SMS
                requestPermissions(arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST_CODE)
            }
        }

        // Check permission when the activity is created
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Request permission to send SMS if not granted
            requestPermissions(arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST_CODE)
        }
    }


    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(applicationContext, "SMS sent successfully!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Failed to send SMS!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send SMS
                val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)
                val editTextMessage = findViewById<EditText>(R.id.editTextMessage)
                val phoneNumber = editTextPhoneNumber.text.toString()
                val message = editTextMessage.text.toString()
                sendSMS(phoneNumber, message)
            } else {
                // Permission denied, show a message
                Toast.makeText(applicationContext, "Permission denied! Can't send SMS.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

