package com.example.homework_1_activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val items = arrayListOf<NewsItem>(
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

    val listSelect = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()

    }

    fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = NewsAdapter(LayoutInflater.from(this), items, listSelect, this)

        findViewById<View>(R.id.buttonTopic).setOnClickListener {
            val intent = Intent(MainActivity@ this, ActivitySelect::class.java)
            intent.putStringArrayListExtra("list", listSelect)
            startActivity(intent)
        }
        findViewById<View>(R.id.buttonInvite).setOnClickListener {
            val textMessage = "Приглашая тебя мой друг протестировать мое приложение!"
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
            sendIntent.type = "text/plain"
            val title = "Выбор программы для отправки приглашения"
            val chooser = Intent.createChooser(sendIntent, title)
            sendIntent.resolveActivity(packageManager)?.let {
                startActivity(chooser)
            }
        }
    }

    override fun onBackPressed() {
        val bld: AlertDialog.Builder = AlertDialog.Builder(this)
        val ContinueApp =
            DialogInterface.OnClickListener { dialog,
                                              which ->
            }
        val closeApp =
            DialogInterface.OnClickListener { dialog,
                                              which ->
                super.onBackPressed()
            }
        bld.setMessage("Вы хотите закрыть приложение?")
        bld.setTitle("Привет!")
        bld.setNegativeButton("Нет", ContinueApp)
        bld.setPositiveButton("Да", closeApp)
        val dialog: AlertDialog = bld.create()
        dialog.show()
    }
}
