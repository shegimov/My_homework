package com.example.homework_1_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback


class ActivitySelect : AppCompatActivity() {

    val items = arrayListOf<NewsItemSelect>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        val listSelect = getIntent().getStringArrayListExtra("list")
        for (select in listSelect) items.add(NewsItemSelect(select.toString()))
        initRecycler()
    }

    fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSelect)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = NewsAdapterSelect(LayoutInflater.from(this), items)

        val itemDecorate = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        getDrawable(R.drawable.line)?.let { itemDecorate.setDrawable(it) }
        recyclerView.addItemDecoration(itemDecorate)

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                items.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        findViewById<View>(R.id.btnRemove).setOnClickListener() {
            items.removeAt(1)
            recyclerView.adapter?.notifyItemRemoved(1)
        }
    }
}
