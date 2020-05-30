package com.example.task_9

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_9.dataManagment.NewsPreview
import com.example.task_9.dataManagment.NewsRepository
import kotlinx.coroutines.*

import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "dataset"

class HeadlinesFragment : Fragment() {
    var newsList: RecyclerView? = null
    val BUNDLE_NEWSLIST_LAYOUT = "mainactivity.newslist"
    var _rootView: View? = null
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        var newsArray = getNewNewsArray()
//
//        for (news in newsArray){
//            App.newsRepository.create(news)
//        }



        var viewManager = LinearLayoutManager(activity)

        val orientation = activity?.getResources()?.getConfiguration()?.orientation
        val is_phone = resources.getBoolean(R.bool.is_phone)
        if (!is_phone && orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewManager = GridLayoutManager(activity, 2)
        }

        var rootView: View? = null;
        rootView = inflater.inflate(R.layout.fragment_headlines, container, false)

        var recyclerView = rootView?.findViewById<NestedScrollView>(R.id.nestedScrollView)?.findViewById<RecyclerView>(R.id.main_list).apply {
            this?.setHasFixedSize(true)
            this?.layoutManager = viewManager
        }

        job = GlobalScope.launch(context = Dispatchers.Main) {
            val newsArray = App.getNewsFromDb()
            val viewAdapter = NewsListAdapter(newsArray, activity, recyclerView)
            recyclerView?.adapter = viewAdapter;

            recyclerView?.setOnTouchListener { v, event -> onTouch(v, event) }
            newsList = recyclerView
            if (savedInstanceState != null) {
                val savedRecyclerLayoutState = savedInstanceState.getParcelable<HeadlinesFragment.SavedState>(BUNDLE_NEWSLIST_LAYOUT)
                newsList?.scrollToPosition(savedRecyclerLayoutState?.mScrollPosition!!)
            }

        }

        return rootView
    }


    fun getNewNewsArray(): ArrayList<NewsPreview> {
        val newsArray = arrayListOf<NewsPreview>()

        for(i in 1..10) {
            newsArray.add(
                NewsPreview(-1,
                    "News ${i}",
                    Date(116, 4, 24),
                    "Some text ${i}",
                    ""
                )
            )
        }
        return newsArray
    }



    fun onTouch(v: View, event: MotionEvent): Boolean {
        return false
    }


    override fun onResume() {
        super.onResume()
        job = GlobalScope.launch(context = Dispatchers.Main) {
            val newsArray = App.getNewsFromDb()
            val viewAdapter = NewsListAdapter(newsArray, activity, newsList)
            newsList?.adapter = viewAdapter;

//            newsList?.setOnTouchListener { v, event -> onTouch(v, event) }

        }
    }

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState = savedInstanceState.getParcelable<SavedState>(BUNDLE_NEWSLIST_LAYOUT)
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
