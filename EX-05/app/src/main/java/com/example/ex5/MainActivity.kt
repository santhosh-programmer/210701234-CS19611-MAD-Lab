package com.example.ex5
import UsersDBHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etRegisterNumber: EditText
    private lateinit var etName: EditText
    private lateinit var etCGPA: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnModify: Button
    private lateinit var btnDelete: Button
    private lateinit var btnView: Button
    private lateinit var btnClear: Button
    private lateinit var tvDetails: TextView
    private lateinit var dbHelper: UsersDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = UsersDBHelper(this)
        etRegisterNumber = findViewById(R.id.etRegisterNumber)
        etName = findViewById(R.id.etName)
        etCGPA = findViewById(R.id.etCGPA)
        btnAdd = findViewById(R.id.btnAdd)
        btnModify = findViewById(R.id.btnModify)
        btnDelete = findViewById(R.id.btnDelete)
        btnView = findViewById(R.id.btnView)
        btnClear = findViewById(R.id.btnClear)
        tvDetails = findViewById(R.id.tvDetails)
        btnAdd.setOnClickListener {
            insertData()
        }
        btnModify.setOnClickListener {
            updateData()
        }
        btnDelete.setOnClickListener {
            deleteData()
        }

        btnView.setOnClickListener {
            viewData()
        }
        btnClear.setOnClickListener {
            clearFields()
        }
    }
    private fun insertData() {
        val registerNumber = etRegisterNumber.text.toString().toInt()
        val name = etName.text.toString()
        val cgpa = etCGPA.text.toString().toDouble()
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBContract.StudentEntry.COLUMN_REGISTER_NUMBER, registerNumber)
            put(DBContract.StudentEntry.COLUMN_NAME, name)
            put(DBContract.StudentEntry.COLUMN_CGPA, cgpa)
        }
        val newRowId = db?.insert(DBContract.StudentEntry.TABLE_NAME, null, values)
        Toast.makeText(this, "Inserted Row ID: $newRowId",
            Toast.LENGTH_SHORT).show()
    }
    private fun updateData() {
        val registerNumber = etRegisterNumber.text.toString().toInt()
        val name = etName.text.toString()
        val cgpa = etCGPA.text.toString().toDouble()
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBContract.StudentEntry.COLUMN_NAME, name)
            put(DBContract.StudentEntry.COLUMN_CGPA, cgpa)
        }
        val selection = "${DBContract.StudentEntry.COLUMN_REGISTER_NUMBER} = ?"
        val selectionArgs = arrayOf(registerNumber.toString())
        val count = db?.update(
            DBContract.StudentEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
        Toast.makeText(this, "Updated $count rows", Toast.LENGTH_SHORT).show()
    }
    private fun deleteData() {
        val registerNumber = etRegisterNumber.text.toString().toInt()
        val db = dbHelper.writableDatabase
        val selection = "${DBContract.StudentEntry.COLUMN_REGISTER_NUMBER} = ?"
        val selectionArgs = arrayOf(registerNumber.toString())
        val deletedRows = db?.delete(DBContract.StudentEntry.TABLE_NAME, selection,
            selectionArgs)

        Toast.makeText(this, "Deleted $deletedRows rows",
            Toast.LENGTH_SHORT).show()
    }
    private fun viewData() {
        val db = dbHelper.readableDatabase
        val cursor = db?.query(
            DBContract.StudentEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        tvDetails.text = ""
        cursor?.moveToFirst()
        while (cursor?.moveToNext() == true) {
            val registerNumber =
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_REGISTER_NUMBER))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_NAME))
            val cgpa =
                cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.StudentEntry.COLUMN_CGPA))
            tvDetails.append("Register Number: $registerNumber, Name: $name, CGPA:$cgpa\n")
        }
        cursor?.close()
    }
    private fun clearFields() {
        etRegisterNumber.text.clear()
        etName.text.clear()
        etCGPA.text.clear()
    }
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}