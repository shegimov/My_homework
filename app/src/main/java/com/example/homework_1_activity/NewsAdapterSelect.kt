package com.example.homework_1_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class NewsAdapterSelect(
    val inflater: LayoutInflater,
    val itemsSelect: List<NewsItemSelect>):RecyclerView.Adapter<NewsItemViewHolderSelect>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolderSelect {
        return NewsItemViewHolderSelect(inflater.inflate(R.layout.recycler_item_select, parent, false))
    }

    override fun getItemCount() = itemsSelect.size

    override fun onBindViewHolder(holder: NewsItemViewHolderSelect, position: Int) {
        holder.bind(itemsSelect[position])
    }
}