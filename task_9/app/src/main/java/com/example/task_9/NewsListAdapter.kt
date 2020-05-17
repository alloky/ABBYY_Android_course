package com.example.task_9

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.task_9.dataManagment.NewsPreview
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class NewsListAdapter(dataset: List<NewsPreview>, act: FragmentActivity?, rv_: RecyclerView?) : RecyclerView.Adapter<NewsItemViewHolder>() {
    var data : List<NewsPreview> = dataset;
    var curAct : FragmentActivity? = act;
    var rv: RecyclerView? = rv_;

    var job: Job? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false) as LinearLayout

        return NewsItemViewHolder(itemView, curAct, rv)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val cur_item = data[position]

        val formatter = SimpleDateFormat("dd MM yyyy", Locale.CANADA)
        val dateString = formatter.format(cur_item.date)

        holder.setHeader(cur_item.header)
        holder.setDate(dateString)
        holder.setDescription(cur_item.description)
        // TODO : cur_item.imgPath
//        holder.setImgView(R.color.colorPrimaryDark)

        job = GlobalScope.launch(context = Dispatchers.Main) {
            Picasso.with(holder.imgView.context).load(File(cur_item.imgPath)).fit().centerInside().into(holder.imgView)
        }

        holder.id = cur_item.id

    }
}
