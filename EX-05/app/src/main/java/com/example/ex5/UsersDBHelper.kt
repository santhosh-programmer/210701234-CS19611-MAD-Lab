
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ex5.DBContract
class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DBContract.StudentEntry.TABLE_NAME} (" +
                    "${DBContract.StudentEntry.COLUMN_REGISTER_NUMBER} INTEGER PRIMARY KEY," +
        "${DBContract.StudentEntry.COLUMN_NAME} TEXT," +
                "${DBContract.StudentEntry.COLUMN_CGPA} REAL)"
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DBContract.StudentEntry.TABLE_NAME}")
        onCreate(db)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Users.db"
    }
}