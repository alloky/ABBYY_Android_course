package com.example.task_6

import android.annotation.SuppressLint
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
import android.os.Parcelable
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Parcel
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {

    var newsList: RecyclerView? = null
    val BUNDLE_NEWSLIST_LAYOUT = "mainactivity.newslist"
    var pausedState : Bundle? = null

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
        this.newsList = recyclerView
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState = savedInstanceState.getParcelable<SavedState>(BUNDLE_NEWSLIST_LAYOUT)
            newsList?.scrollToPosition(savedRecyclerLayoutState?.mScrollPosition!!)
        }
    }


    fun onTouch(v: View, event:MotionEvent): Boolean {
        return false
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            val savedRecyclerLayoutState = savedInstanceState.getParcelable<SavedState>(BUNDLE_NEWSLIST_LAYOUT)
//            newsList?.layoutManager?.onRestoreInstanceState()
            newsList?.scrollToPosition(savedRecyclerLayoutState?.mScrollPosition!!)
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        var llmngr: LinearLayoutManager = newsList?.layoutManager as LinearLayoutManager
        var p: Parcelable? = llmngr.onSaveInstanceState()
        var pos = llmngr.findLastCompletelyVisibleItemPosition()

        if (p != null) {
            var sst: SavedState = SavedState(p)
            sst.mScrollPosition = pos

            outState.putParcelable(
                BUNDLE_NEWSLIST_LAYOUT,
                sst
            )

        }

        super.onSaveInstanceState(outState)
    }

//    override fun onPause() {
//        super.onPause()
//        pausedState = Bundle()
//        onSaveInstanceState(pausedState!!)
//    }

//    override fun onResume() {
//        super.onResume()
//        onRestoreInstanceState(pausedState)
//    }

    internal class SavedState : android.view.View.BaseSavedState {
        var mScrollPosition: Int = 0

        constructor(`in`: Parcel) : super(`in`) {
            mScrollPosition = `in`.readInt()
        }

        constructor(superState: Parcelable) : super(superState) {}

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(mScrollPosition)
        }

        companion object {
            @SuppressLint("ParcelCreator")
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

}
