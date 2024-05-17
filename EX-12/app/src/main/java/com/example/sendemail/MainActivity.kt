package com.example.sendemail
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextSubject: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSendEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextSubject = findViewById(R.id.editTextSubject)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSendEmail = findViewById(R.id.buttonSendEmail)

        buttonSendEmail.setOnClickListener {
            val recipientEmail = editTextEmailAddress.text.toString()
            val subject = editTextSubject.text.toString()
            val body = editTextMessage.text.toString()

            SendEmailTask().execute(recipientEmail, subject, body)
        }
    }

    private inner class SendEmailTask : AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg params: String): Void? {
            val recipientEmail = params[0]
            val subject = params[1]
            val body = params[2]

            EmailSender.sendEmail(recipientEmail, subject, body)
            return null
        }


    }
}
