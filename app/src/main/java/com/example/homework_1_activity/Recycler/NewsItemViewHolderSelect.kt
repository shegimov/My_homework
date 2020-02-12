package com.example.homework_1_activity.Recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1_activity.R

class NewsItemViewHolderSelect(itemView:View):RecyclerView.ViewHolder(itemView) {
    val textNameSelect: TextView = itemView.findViewById(R.id.textSelect)

    fun bind(item: NewsItemSelect){
        textNameSelect.text = item.nameSelect
    }
}