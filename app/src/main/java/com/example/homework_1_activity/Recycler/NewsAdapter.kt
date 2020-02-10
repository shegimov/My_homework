package com.example.homework_1_activity.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1_activity.R

class NewsAdapter(
    val inflater: LayoutInflater,
    val items: List<NewsItem>,
    private val listener: ((newsItem: NewsItem) -> Unit)?,
    private val listenerList: ((listSelect: String) -> Unit)?
) : RecyclerView.Adapter<NewsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            inflater.inflate(
                R.layout.recycler_item_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.imageIv.setOnClickListener() {
            listenerList?.invoke(items[position].nameFilm)
        }
        holder.buttonIv.setOnClickListener {
            listener?.invoke(items[position])
        }
        holder.bind(items[position])
    }

}