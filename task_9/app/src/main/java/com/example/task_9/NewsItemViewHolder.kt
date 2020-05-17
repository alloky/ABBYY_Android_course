package com.example.task_9

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File

class NewsItemViewHolder(itemView: LinearLayout, act: FragmentActivity?, rv: RecyclerView?) : RecyclerView.ViewHolder(itemView) {
    private val header: TextView = itemView.findViewById(R.id.news_header)
    private val date: TextView = itemView.findViewById(R.id.news_date)
    private val description: TextView = itemView.findViewById(R.id.news_text)
    public val imgView: ImageView = itemView.findViewById(R.id.news_image)

    public var id : Int = -1

    init {
        itemView.setOnClickListener(View.OnClickListener(){ it: View? ->
            if (it != null)  {
                onNewsItemClick(it, act, rv)
            }
        })
    }

    public fun setHeader(header: String){
        this.header.text = header
    }

    public fun setDate(dt: String){
        this.date.text = dt
    }

    public fun setDescription(descr: String){
        this.description.text = descr
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public fun setImgView(imagePath: String){

//        Picasso.with(act).load(File(imagePath)).fit().centerInside().into(this.imgView)
    }

    fun onNewsItemClick(view: View, act: FragmentActivity?, rv: RecyclerView?) {
        var b = Bundle()
        b.putInt("ID", id)
        var f = NewsContentFragment.newInstance(id)
        val fragmentManager = act?.getSupportFragmentManager()
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.details, f)
        fragmentTransaction?.addToBackStack(f.javaClass.getName())
        fragmentTransaction?.commit()

        if (rv != null) {
            ViewCompat.setOverScrollMode(rv, ViewCompat.OVER_SCROLL_NEVER)
        }

    }

}