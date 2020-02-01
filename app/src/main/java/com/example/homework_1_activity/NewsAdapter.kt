package com.example.homework_1_activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    val inflater: LayoutInflater,
    val items: List<NewsItem>,
    val listSelect: ArrayList<String>,
    val parentContext: Activity
) : RecyclerView.Adapter<NewsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(inflater.inflate(R.layout.recycler_item_news, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.imageIv.setOnLongClickListener() {
            listSelect.add(items[position].nameFilm)
        }
        holder.buttonIv.setOnClickListener {
            val intent = Intent(parentContext, DescriptionActivity::class.java)
            intent.putExtra("key", Film(items[position].pictureName, items[position].description))
            startActivityForResult(parentContext, intent, 1, Bundle())
        }
        Log.d("List select", "list select $listSelect")
        holder.bind(items[position])
    }

}