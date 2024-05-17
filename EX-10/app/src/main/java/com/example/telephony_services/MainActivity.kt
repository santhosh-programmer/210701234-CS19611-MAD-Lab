package com.example.telephony_services

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION = 100 // You can choose any value
    private lateinit var textViewTelephonyInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewTelephonyInfo = findViewById(R.id.textViewTelephonyInfo)

        // Check if the permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, proceed with getting telephony information
            getTelephonyInfo()
        } else {
            // Permission is not granted, request it from the user
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_CODE_PERMISSION)
        }
    }

    private fun getTelephonyInfo() {
        try {
            val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

            val networkType = when (telephonyManager.networkType) {
                TelephonyManager.NETWORK_TYPE_GPRS -> "GPRS"
                TelephonyManager.NETWORK_TYPE_EDGE -> "EDGE"
                TelephonyManager.NETWORK_TYPE_UMTS -> "UMTS"
                TelephonyManager.NETWORK_TYPE_HSDPA -> "HSDPA"
                TelephonyManager.NETWORK_TYPE_HSUPA -> "HSUPA"
                TelephonyManager.NETWORK_TYPE_HSPA -> "HSPA"
                TelephonyManager.NETWORK_TYPE_CDMA -> "CDMA"
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> "EVDO 0"
                TelephonyManager.NETWORK_TYPE_EVDO_A -> "EVDO A"
                TelephonyManager.NETWORK_TYPE_EVDO_B -> "EVDO B"
                TelephonyManager.NETWORK_TYPE_1xRTT -> "1xRTT"
                TelephonyManager.NETWORK_TYPE_LTE -> "LTE"
                TelephonyManager.NETWORK_TYPE_IWLAN -> "IWLAN"
                else -> "Unknown"
            }

            val telephonyInfo = """
                Device ID: ${telephonyManager.deviceId}
                Phone Type: ${when (telephonyManager.phoneType) {
                TelephonyManager.PHONE_TYPE_NONE -> "None"
                TelephonyManager.PHONE_TYPE_GSM -> "GSM"
                TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
                TelephonyManager.PHONE_TYPE_SIP -> "SIP"
                else -> "Unknown"
            }}
                Network Type: $networkType
                SIM Serial Number: ${telephonyManager.simSerialNumber}
                SIM Operator Name: ${telephonyManager.simOperatorName}
                Software Version: ${if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) telephonyManager.imei else telephonyManager.deviceSoftwareVersion}
            """.trimIndent()

            textViewTelephonyInfo.text = telephonyInfo
        } catch (e: SecurityException) {
            // Handle SecurityException here
            // For example, inform the user that the permission is required to access telephony information
            textViewTelephonyInfo.text = "Permission required to access telephony information"
        }
    }

    // Handle the result of the permission request
    // Handle the result of the permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults) // Call superclass method

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed with getting telephony information
                getTelephonyInfo()
            } else {
                // Permission is denied, inform the user or take alternative action
                // You can display a message or disable functionality dependent on this permission
                textViewTelephonyInfo.text = "Permission denied to access telephony information"
            }
        }
    }

}
