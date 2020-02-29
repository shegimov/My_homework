package com.example.homework_1_activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1_activity.Recycler.NewsAdapter
import com.example.homework_1_activity.Recycler.NewsItem

class NewListFragment : Fragment() {

    var listener: OnNewsClickListener? = null
    var listenerList: OnNewListSelect? = null

    val items = listOf(
        NewsItem(
            "Фильм №1 - Алита",
            "Действие фильма происходит через 300 лет после Великой войны в XXVI веке. Доктор Идо находит останки женщины-киборга. После починки киборг ничего не помнит, но обнаруживает, что в состоянии пользоваться боевыми приемами киборгов. Начинаются поиски утерянных воспоминаний.",
            R.drawable.alita
        ),
        NewsItem(
            "Фильм №2 - Аладин",
            "Молодой воришка по имени Аладдин хочет стать принцем, чтобы жениться на принцессе Жасмин. Тем временем визирь Аграбы Джафар, намеревается захватить власть над Аграбой, а для этого он стремится заполучить волшебную лампу, хранящуюся в пещере чудес, доступ к которой разрешен лишь тому, кого называют «алмаз неограненный», и этим человеком является никто иной как сам Аладдин.",
            R.drawable.aladin
        ),
        NewsItem(
            "Фильм №3 - Мстители",
            "Оставшиеся в живых члены команды Мстителей и их союзники должны разработать новый план, который поможет противостоять разрушительным действиям могущественного титана Таноса. После наиболее масштабной и трагической битвы в истории они не могут допустить ошибку.",
            R.drawable.marvel
        ),
        NewsItem(
            "Фильм №4 - Т34",
            "Во времена величайших испытаний человечества, когда от каждого действия зависят жизни любимых, два заклятых врага начнут свое противостояние. Оказавшись в плену, вчерашний курсант Ивушкин планирует дерзкий побег. Он собирает свой экипаж и бросает вызов немецким танковым ассам во главе с Ягером. Ради своей любви и Родины он готов идти до конца.",
            R.drawable.t34
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = NewsAdapter(
            LayoutInflater.from(context),
            items, { listener?.onNewsClick(it) }, { listenerList?.openNewsSelected(it) })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is OnNewsClickListener) {
            listener = activity as OnNewsClickListener
        } else {
            throw Exception("Activity must implement OnNewsClickListener")
        }
        if (activity is OnNewListSelect) {
            listenerList = activity as OnNewListSelect
        } else {
            throw Exception("Activity must implement OnNewListSelect")
        }
    }

    companion object {
        const val TAG = "NewsListFragment"
    }

    interface OnNewsClickListener {
        fun onNewsClick(item: NewsItem)
    }

    interface OnNewListSelect {
        fun openNewsSelected(listSelect: String)
    }

}