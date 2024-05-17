package com.example.sendemail
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailSender {
    fun sendEmail(recipientEmail: String, subject: String, body: String) {
        val properties = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                // Replace these with your actual email credentials
                return PasswordAuthentication("210701275@rajalakshmi.edu.in", "aQJchi-1W911va")
            }
        })

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress("210701275@rajalakshmi.edu.in"))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(recipientEmail))
            message.subject = subject
            message.setText(body)

            Transport.send(message)
            println("Email sent successfully.")
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}

