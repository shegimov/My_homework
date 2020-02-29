package com.example.homework_1_activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewDetailedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.imageFilm)
            .setImageResource(arguments!!.getInt("Pictures"))
        view.findViewById<TextView>(R.id.description_text).text =
            arguments?.getString("Description", "")
    }

    companion object {
        const val TAG = "NewsDetailedFragment"

        fun newInstance(pictires: Int, description: String): NewDetailedFragment {
            val fragment = NewDetailedFragment()

            val bundle = Bundle()
            bundle.putInt("Pictures", pictires)
            bundle.putString("Description", description)

            fragment.arguments = bundle

            return fragment
        }
    }
}