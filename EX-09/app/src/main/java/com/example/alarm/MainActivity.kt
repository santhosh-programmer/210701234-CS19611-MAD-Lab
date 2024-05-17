package com.example.alarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var analogClock: AnalogClockView
    private lateinit var buttonSetTime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analogClock = findViewById(R.id.analogClock)
        buttonSetTime = findViewById(R.id.buttonSetAlarm) // Change this to R.id.buttonSetTime


        buttonSetTime.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // Set the selected time on the analog clock
                analogClock.setTime(hourOfDay, minute)
            },
            currentHour,
            currentMinute,
            true
        )

        timePickerDialog.show()
    }
}
