package com.example.task_9

import android.app.Application
import com.example.task_9.dataManagment.AppSqliteOpenHelper
import com.example.task_9.dataManagment.DatabaseHolder
import com.example.task_9.dataManagment.NewsPreview
import com.example.task_9.dataManagment.NewsRepository

class App: Application() {
    companion object {
        lateinit var newsRepository: NewsRepository private set

        public suspend fun getNewsFromDb(): ArrayList<NewsPreview> {
            return App.newsRepository.loadAll() as ArrayList<NewsPreview>
        }

        public suspend fun getNewsById(id: Int): NewsPreview {
            return App.newsRepository.getById(id) as NewsPreview
        }

        public suspend fun addNews(preview: NewsPreview) {
            App.newsRepository.create(preview)
        }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository = NewsRepository(AppSqliteOpenHelper(this))
    }


}