package com.example.ex5
import android.provider.BaseColumns
class DBContract {
    class StudentEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Student"
            const val COLUMN_REGISTER_NUMBER = "RegisterNumber"
            const val COLUMN_NAME = "Name"
            const val COLUMN_CGPA = "CGPA"
        }

    }
}
