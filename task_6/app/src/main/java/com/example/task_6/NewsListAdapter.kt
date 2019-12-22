package com.example.task_6

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.media.Image
import android.os.Build
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*
import kotlin.math.log


class NewsListAdapter(dataset: List<NewsPreview>) : RecyclerView.Adapter<NewsListAdapter.ItemViewHolder>() {
    var data : List<NewsPreview> = dataset;
    var context : Context? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        this.context = parent.context

        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false) as LinearLayout

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var cur_item = data[position]

        val formatter = SimpleDateFormat("dd MM yyyy", Locale.CANADA)
        val dateString = formatter.format(cur_item.date)

        holder.setHeader(cur_item.header + position)
        holder.setDate(dateString)
        holder.setDescription(cur_item.description + position)
        holder.setImgView(cur_item.imgId)

    }



    class ItemViewHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView) {
        private var header: TextView = itemView.findViewById(R.id.news_header)
        private var date: TextView = itemView.findViewById(R.id.news_date)
        private var description: TextView = itemView.findViewById(R.id.news_text)
        private var imgView: ImageView = itemView.findViewById(R.id.news_image)

        init {
            itemView.setOnClickListener(View.OnClickListener(){ it: View? ->
                if (it != null)  {
                    onNewsItemClick(it)
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
        public fun setImgView(imgId: Int){
            this.imgView.setBackgroundColor(imgId)
        }

        fun onNewsItemClick(view: View) {
            val i = Intent(itemView.context, NewsContentActivity::class.java)
            i.putExtra("CONTENT", description.text);
            i.putExtra("HEADER", header.text);
            itemView.context.startActivity(i)
        }

    }

}
