package com.example.task_9.dataManagment

import androidx.annotation.NonNull

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewsRepository(@param:NonNull private val sqlHelper: AppSqliteOpenHelper) {

    private val SELECT_KEYS = arrayOf(NewsPreviewContract.Columns._ID as String,
            NewsPreviewContract.Columns.HEADER,
            NewsPreviewContract.Columns.CONTENT,
            NewsPreviewContract.Columns.DATE,
            NewsPreviewContract.Columns.IMG_PATH)

    fun create(@NonNull newsPreview: NewsPreview) {
        try {
            val database = sqlHelper.writableDatabase
//            val database = databaseHolder.open()

            val contentValues = ContentValues()

            contentValues.put(NewsPreviewContract.Columns.HEADER, newsPreview.header)
            contentValues.put(NewsPreviewContract.Columns.CONTENT, newsPreview.description)
            contentValues.put(NewsPreviewContract.Columns.IMG_PATH, newsPreview.imgPath)
            val dateFormat = SimpleDateFormat(NewsPreview.dateFromat)
            val dateStringifyed = dateFormat.format(newsPreview.date)
            contentValues.put(NewsPreviewContract.Columns.DATE, dateStringifyed)


            database?.insert(
                NewsPreviewContract.TABLE_NAME,
                null, contentValues
            )
        } catch (e: Exception){
            println(e.stackTrace)
        }
        finally {
        }
    }

    fun getById(id: Int): NewsPreview? {
        val db = sqlHelper.readableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(
            NewsPreviewContract.TABLE_NAME,
            SELECT_KEYS,
            selection,
            selectionArgs,
            null,
            null,
            null
        )


        cursor.use {
            with(it) {
                if (moveToFirst()) {
                    val id = cursor.getInt(cursor.getColumnIndex(NewsPreviewContract.Columns._ID as String))
                    val header = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.HEADER))
                    val description = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.CONTENT))
                    val imgPath = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.IMG_PATH))
                    val dateStr = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.DATE))

                    val format = SimpleDateFormat(NewsPreview.dateFromat);
                    var date = Date()
                    try {
                        val parsed =  format.parse(dateStr)
                        if (parsed != null){
                            date = parsed
                        }
                    } catch (e : ParseException) {

                    }

                    return NewsPreview(id, header, date, description, imgPath)
                }
                return null
            }
        }
    }

    fun loadAll(): List<NewsPreview> {
        val newsPreviewList = ArrayList<NewsPreview>()
        var cursor: Cursor? = null
        try {
            val database = sqlHelper.readableDatabase

            cursor = database?.query(
                NewsPreviewContract.TABLE_NAME,
                SELECT_KEYS,
                null,
                null,
                null,
                null,
                null
            )

            while (cursor!!.moveToNext()) {

                val id = cursor.getInt(cursor.getColumnIndex(NewsPreviewContract.Columns._ID as String))
                val header = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.HEADER))
                val description = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.CONTENT))
                val imgPath = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.IMG_PATH))
                val dateStr = cursor.getString(cursor.getColumnIndex(NewsPreviewContract.Columns.DATE))

                val format = SimpleDateFormat(NewsPreview.dateFromat);
                var date = Date()
                try {
                    val parsed =  format.parse(dateStr)
                    if (parsed != null){
                        date = parsed
                    }
                } catch (e : ParseException) {

                }
                val newsPreview = NewsPreview(id, header, date, description, imgPath)

                newsPreviewList.add(newsPreview)
            }
        } finally {
            cursor?.close()
        }

        return newsPreviewList
    }
}
