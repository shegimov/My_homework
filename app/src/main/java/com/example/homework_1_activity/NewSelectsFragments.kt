package com.example.homework_1_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1_activity.Recycler.NewsAdapterSelect
import com.example.homework_1_activity.Recycler.NewsItemSelect

class NewSelectsFragments : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recyclerViewSelect).adapter = NewsAdapterSelect(
            LayoutInflater.from(context), items
        )
        initRecycler(view)
    }

    fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewSelect)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = NewsAdapterSelect(LayoutInflater.from(activity), items)

        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (items.size > 0) {
                    val position = viewHolder.adapterPosition
                    items.removeAt(position)
                    recyclerView.adapter?.notifyItemRemoved(position)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    companion object {
        const val TAG = "NewsSelectFragment"
        val items = arrayListOf<NewsItemSelect>()

        fun newInstance(listSelect: ArrayList<String>): NewSelectsFragments {
            for (select in 0..listSelect.size - 1) items.add(
                NewsItemSelect(
                    listSelect[select]
                )
            )
            val fragment = NewSelectsFragments()
            return fragment
        }
    }
}