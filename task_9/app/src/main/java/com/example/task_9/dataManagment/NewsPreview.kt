package com.example.task_9.dataManagment

import java.util.*

data class NewsPreview(var id: Int, var header:String, var date: Date, var description: String, var imgPath: String) {
    companion object {
        const val dateFromat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}