package com.example.task_9

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewsContentFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewsContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsContentFragment : Fragment() {


    private var header: String? = null
    private var content: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val orientation = getResources().getConfiguration().orientation

        var rootView: View? = null

        content = arguments?.getString("CONTENT")
        header = arguments?.getString("HEADER")

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            rootView = inflater.inflate(R.layout.activity_news_content, container, false)

            val itemView = rootView.findViewById<LinearLayout>(R.id.news_content_body)

            setupLayout(rootView, header, content)
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rootView = inflater.inflate(R.layout.activity_news_content_album, container, false)

            val itemView = rootView.findViewById<ConstraintLayout>(R.id.news_content_body)

            setupLayout(rootView, header, content)
        }

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("CONTENT")
        header = arguments?.getString("HEADER")

    }

//    private fun setupOritentation( orientation: Int,  root: View) {
//
//    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//
//        setupOritentation(newConfig.orientation)
//    }

    fun setupLayout(itemView: View, hdr: String?, conent: String?){
        val header: TextView = itemView.findViewById(R.id.news_header)
        val description: TextView = itemView.findViewById(R.id.news_text)

        if (conent != null) {
            description.setText(conent)
        }

        if (hdr != null) {
            header.setText(hdr)
        }

    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val orientation = getResources().getConfiguration().orientation
//        setupOritentation(orientation)
//
//    }

    companion object {

        @JvmStatic
        fun newInstance(header: String?, content: String?) = NewsContentFragment().apply {
            arguments = Bundle().apply {
                putString("HEADER",  header)
                putString("CONTENT", content)
            }
        }
    }
}
