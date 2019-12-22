package com.example.task_6

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class NewsContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var orientation = getResources().getConfiguration().orientation

        setupOritentation(orientation)

    }

    private fun setupOritentation( orientation: Int) {
        var content = getIntent().getStringExtra("CONTENT")
        var header = getIntent().getStringExtra("HEADER")

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_news_content)

            var itemView = findViewById<LinearLayout>(R.id.news_content_body)

            setupLayout(itemView, content)
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_news_content_album)

            var itemView = findViewById<ConstraintLayout>(R.id.news_content_body)

            setupLayout(itemView, content)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        setupOritentation(newConfig.orientation)
    }

    fun setupLayout(itemView: View, conent: String?){
        var header: TextView = itemView.findViewById(R.id.news_header)
        var date: TextView = itemView.findViewById(R.id.news_date)
        var description: TextView = itemView.findViewById(R.id.news_text)
        var imgView: ImageView = itemView.findViewById(R.id.news_image)

        header.setText("Hello")

        if (conent != null) {
            description.setText(conent)
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var orientation = getResources().getConfiguration().orientation
        setupOritentation(orientation)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState)
    }
}
