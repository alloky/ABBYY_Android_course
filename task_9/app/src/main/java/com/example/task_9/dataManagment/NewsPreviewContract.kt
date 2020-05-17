package com.example.task_9.dataManagment


import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.annotation.NonNull

public val NewsPreviewContract.Columns.Companion._ID: Any?
    get() {
        return BaseColumns._ID
    }

object NewsPreviewContract {

 val TABLE_NAME = "person_table"

 interface Columns:BaseColumns {
    companion object {
        val HEADER = "header"
        val CONTENT = "content"
        val DATE = "date"
        val IMG_PATH = "imgpath"
    }
 }

 fun createTable(@NonNull db:SQLiteDatabase) {
    db.execSQL(
        "CREATE TABLE " + TABLE_NAME
        + " ( "
        + Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + Columns.HEADER + " TEXT NOT NULL,"
        + Columns.CONTENT + " TEXT NOT NULL,"
        + Columns.DATE + " TEXT NOT NULL,"
        + Columns.IMG_PATH + " TEXT NOT NULL"
        + " );"
    )
}
}// Утилитный класс
