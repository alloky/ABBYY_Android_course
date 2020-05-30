package com.example.task_9

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File


class NewsContentFragment : Fragment() {


    private var news_id: Int? = null
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View? = null

        val is_phone = resources.getBoolean(R.bool.is_phone)
        val orientation = getResources().getConfiguration().orientation

        news_id = arguments?.getInt("ID")

        if (news_id == null && !is_phone && orientation == Configuration.ORIENTATION_LANDSCAPE){
            rootView = inflater.inflate(R.layout.activity_news_content_empty, container, false)
            return rootView
        }

        rootView = inflater.inflate(R.layout.activity_news_content, container, false)
        setupLayout(rootView)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        news_id = arguments?.getInt("ID")
    }

    fun setupLayout(itemView: View){
        val header: TextView = itemView.findViewById(R.id.news_header)
        val description: TextView = itemView.findViewById(R.id.news_text)
        val image: ImageView = itemView.findViewById(R.id.news_image)
        news_id?.let {

            job = GlobalScope.launch(context = Dispatchers.Main) {
                val newsData = App.getNewsById(it)

                description.setText(newsData.description)
                header.setText(newsData.header)
                Picasso.with(context).load(File(newsData.imgPath)).fit().centerInside().into(image)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = NewsContentFragment().apply {
            arguments = Bundle().apply {
                putInt("ID",  id)
            }
        }
    }
}
