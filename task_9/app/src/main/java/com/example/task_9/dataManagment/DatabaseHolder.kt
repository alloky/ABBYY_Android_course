package com.example.task_9.dataManagment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.NonNull

import java.util.concurrent.locks.ReentrantLock

class DatabaseHolder(@NonNull context: Context) {
    private val appSqliteOpenHelper: AppSqliteOpenHelper = AppSqliteOpenHelper(context)

    private var sqLiteDatabase: SQLiteDatabase? = null

    private var databaseOpenCloseBalance: Int = 0

    private val reentrantLock = ReentrantLock()

    fun open(): SQLiteDatabase? {
        try {
            reentrantLock.lock()
            if (databaseOpenCloseBalance == 0) {
                sqLiteDatabase = appSqliteOpenHelper.writableDatabase
            }

            ++databaseOpenCloseBalance

            return sqLiteDatabase
        } finally {
            reentrantLock.unlock()
        }
    }

    fun close() {
        try {
            reentrantLock.lock()
            --databaseOpenCloseBalance

            if (databaseOpenCloseBalance == 0) {
                sqLiteDatabase!!.close()
                sqLiteDatabase = null
            }
        } finally {
            reentrantLock.unlock()
        }
    }
}
