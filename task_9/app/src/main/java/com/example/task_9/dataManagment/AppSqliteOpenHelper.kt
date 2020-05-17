package com.example.task_9.dataManagment

import androidx.annotation.NonNull
import androidx.annotation.Nullable

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppSqliteOpenHelper(@Nullable context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(@NonNull db: SQLiteDatabase) {
        NewsPreviewContract.createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        NewsPreviewContract.createTable(db)
    }

    companion object {

        private val DATABASE_NAME = "SampleDatabase.db"
        private val VERSION = 1
    }
}
