package com.example.task_9

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.*




private const val ARG_PARAM1 = "dataset"

class HeadlinesFragment : Fragment() {
    var newsList: RecyclerView? = null
    val BUNDLE_NEWSLIST_LAYOUT = "mainactivity.newslist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var myDataset = arrayListOf<NewsPreview>()

        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))
        myDataset.add(NewsPreview("News", Date(116, 4, 24), "Some text", R.color.colorPrimaryDark))

        val rootView = inflater.inflate(R.layout.fragment_headlines, container, false)

        var viewManager = LinearLayoutManager(activity)

//        var recyclerView = (RecyclerView) rootView.findViewById(R.id.main_list);
        var recyclerView = rootView.findViewById<NestedScrollView>(R.id.nestedScrollView).findViewById<RecyclerView>(R.id.main_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            this?.setHasFixedSize(true)

            // use a linear layout manager
            this?.layoutManager = viewManager

//            // specify an viewAdapter (see also next example)
//            this?.adapter = viewAdapter
        }

        var viewAdapter = NewsListAdapter(myDataset, activity, recyclerView)
        recyclerView.adapter = viewAdapter;

        recyclerView?.setOnTouchListener { v, event -> onTouch(v, event) }
        this.newsList = recyclerView
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState = savedInstanceState.getParcelable<HeadlinesFragment.SavedState>(BUNDLE_NEWSLIST_LAYOUT)
            newsList?.scrollToPosition(savedRecyclerLayoutState?.mScrollPosition!!)
        }

        return rootView
    }


    fun onTouch(v: View, event: MotionEvent): Boolean {
        return false
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
