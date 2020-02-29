package com.example.homework_1_activity.Recycler

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1_activity.R

class NewsItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textNameFilm: TextView = itemView.findViewById(R.id.textNameFilm)
    val textDetail: TextView = itemView.findViewById(R.id.textDetail)
    val imageIv: ImageView = itemView.findViewById(R.id.imgIv)
    val buttonIv: Button = itemView.findViewById(R.id.buttonDetails)
    val imageHeart: ImageView = itemView.findViewById(R.id.heart)

    fun bind(item: NewsItem){
        textNameFilm.text = item.nameFilm
        textDetail.text = item.description
        imageIv.setImageResource(item.pictureName)
    }
}