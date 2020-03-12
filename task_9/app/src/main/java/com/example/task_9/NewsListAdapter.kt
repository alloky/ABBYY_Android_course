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
import java.text.SimpleDateFormat
import java.util.*


class NewsListAdapter(dataset: List<NewsPreview>, act: FragmentActivity?, rv_: RecyclerView?) : RecyclerView.Adapter<NewsListAdapter.ItemViewHolder>() {
    var data : List<NewsPreview> = dataset;
    var curAct : FragmentActivity? = act;
    var rv: RecyclerView? = rv_;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false) as LinearLayout

        return ItemViewHolder(itemView, curAct, rv)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cur_item = data[position]

        val formatter = SimpleDateFormat("dd MM yyyy", Locale.CANADA)
        val dateString = formatter.format(cur_item.date)

        holder.setHeader(cur_item.header + position)
        holder.setDate(dateString)
        holder.setDescription(cur_item.description + position)
        holder.setImgView(cur_item.imgId)

    }



    class ItemViewHolder(itemView: LinearLayout, act: FragmentActivity?, rv: RecyclerView?) : RecyclerView.ViewHolder(itemView) {
        private val header: TextView = itemView.findViewById(R.id.news_header)
        private val date: TextView = itemView.findViewById(R.id.news_date)
        private val description: TextView = itemView.findViewById(R.id.news_text)
        private val imgView: ImageView = itemView.findViewById(R.id.news_image)

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
        public fun setImgView(imgId: Int){
            this.imgView.setBackgroundColor(imgId)
        }

        fun onNewsItemClick(view: View, act: FragmentActivity?, rv: RecyclerView?) {
//            val i = Intent(itemView.context, NewsContentActivity::class.java)
//            i.putExtra("CONTENT", description.text);
//            i.putExtra("HEADER", header.text);
//            itemView.context.startActivity(i)

            var b = Bundle()
            b.putString("CONTENT", description.text.toString())
            b.putString("HEADER", header.text.toString())
            var f = NewsContentFragment.newInstance(header.text.toString(), description.text.toString())
            val fragmentManager = act?.getSupportFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.headlines_fragment, f)
            fragmentTransaction?.addToBackStack(f.javaClass.getName())
            fragmentTransaction?.commit()

            if (rv != null) {
                ViewCompat.setOverScrollMode(rv, ViewCompat.OVER_SCROLL_NEVER)
            };

        }

    }

}
