package com.example.task_6

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var myDataset = arrayListOf<NewsPreview>()

        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))

        var viewManager = LinearLayoutManager(this)
        var viewAdapter = NewsListAdapter(myDataset)

        var recyclerView = findViewById<RecyclerView>(R.id.main_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        recyclerView.setOnTouchListener { v, event -> onTouch(v, event) }

    }

    fun onTouch(v: View, event:MotionEvent): Boolean {
        return false
    }


}
