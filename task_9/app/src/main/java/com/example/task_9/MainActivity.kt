package com.example.task_9

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var newsList: RecyclerView? = null
    val BUNDLE_NEWSLIST_LAYOUT = "mainactivity.newslist"
    var pausedState : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = getResources().getConfiguration().orientation

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val fragmentManager = getSupportFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = HeadlinesFragment()
            fragmentTransaction?.replace(R.id.headlines_fragment, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun openCameraActivity(view: View) {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

//    public override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        if (savedInstanceState != null) {
//            val savedRecyclerLayoutState = savedInstanceState.getParcelable<SavedState>(BUNDLE_NEWSLIST_LAYOUT)
////            newsList?.layoutManager?.onRestoreInstanceState()
//            newsList?.scrollToPosition(savedRecyclerLayoutState?.mScrollPosition!!)
//        }
//    }
//
//    public override fun onSaveInstanceState(outState: Bundle) {
//        var llmngr: LinearLayoutManager = newsList?.layoutManager as LinearLayoutManager
//        var p: Parcelable? = llmngr.onSaveInstanceState()
//        var pos = llmngr.findLastCompletelyVisibleItemPosition()
//
//        if (p != null) {
//            var sst: SavedState = SavedState(p)
//            sst.mScrollPosition = pos
//
//            outState.putParcelable(
//                BUNDLE_NEWSLIST_LAYOUT,
//                sst
//            )
//
//        }
//
//        super.onSaveInstanceState(outState)
//    }
//
////    override fun onPause() {
////        super.onPause()
////        pausedState = Bundle()
////        onSaveInstanceState(pausedState!!)
////    }
//
////    override fun onResume() {
////        super.onResume()
////        onRestoreInstanceState(pausedState)
////    }
//
//    internal class SavedState : android.view.View.BaseSavedState {
//        var mScrollPosition: Int = 0
//
//        constructor(`in`: Parcel) : super(`in`) {
//            mScrollPosition = `in`.readInt()
//        }
//
//        constructor(superState: Parcelable) : super(superState) {}
//
//        override fun writeToParcel(dest: Parcel, flags: Int) {
//            super.writeToParcel(dest, flags)
//            dest.writeInt(mScrollPosition)
//        }
//
//        companion object {
//            @SuppressLint("ParcelCreator")
//            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
//                override fun createFromParcel(`in`: Parcel): SavedState {
//                    return SavedState(`in`)
//                }
//
//                override fun newArray(size: Int): Array<SavedState?> {
//                    return arrayOfNulls(size)
//                }
//            }
//        }
//    }

}
