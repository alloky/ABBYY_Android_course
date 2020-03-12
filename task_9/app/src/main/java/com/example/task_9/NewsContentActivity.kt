package com.example.task_9

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import android.view.View
import android.widget.LinearLayout


class NewsContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = getResources().getConfiguration().orientation
        setupOritentation(orientation)
    }

    private fun setupOritentation( orientation: Int) {
        val content = getIntent().getStringExtra("CONTENT")
        val header = getIntent().getStringExtra("HEADER")

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_news_content)

            val itemView = findViewById<LinearLayout>(R.id.news_content_body)

            setupLayout(itemView, header, content)
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_news_content_album)

            val itemView = findViewById<ConstraintLayout>(R.id.news_content_body)

            setupLayout(itemView, header, content)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        setupOritentation(newConfig.orientation)
    }

    fun setupLayout(itemView: View, hdr: String?, conent: String?){
        val header: TextView = itemView.findViewById(R.id.news_header)
        val description: TextView = itemView.findViewById(R.id.news_text)

        if (conent != null) {
            description.setText(conent)
        }

        if (hdr != null) {
            header.setText(hdr)
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val orientation = getResources().getConfiguration().orientation
        setupOritentation(orientation)

    }

}
