package com.example.homework_1_activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var changeColorAladin: Boolean = false
    var changeColorAlita: Boolean = false
    var changeColorMarvel: Boolean = false
    var changeColorT34: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ClickAladin(view: View?) {
        changeColorAladin = true
        textFilmAladin.setTextColor(Color.parseColor("#FFFFFF"))
        OpenFirstFilmActivity()
    }

    private fun OpenFirstFilmActivity() {
        val intent = Intent(this@MainActivity, DescriptionActivity::class.java)
        intent.putExtra(
            "key",
            Film(
                R.drawable.aladin,
                "Молодой воришка по имени Аладдин хочет стать принцем, чтобы жениться на принцессе Жасмин. Тем временем визирь Аграбы Джафар, намеревается захватить власть над Аграбой, а для этого он стремится заполучить волшебную лампу, хранящуюся в пещере чудес, доступ к которой разрешен лишь тому, кого называют «алмаз неограненный», и этим человеком является никто иной как сам Аладдин."
            )
        )
        startActivityForResult(intent, 1)
    }

    fun ClickAlita(view: View?) {
        changeColorAlita = true
        textFilmAlita.setTextColor(Color.parseColor("#FFFFFF"))
        OpenSecondFilmActivity()
    }

    private fun OpenSecondFilmActivity() {
        val intent = Intent(this@MainActivity, DescriptionActivity::class.java)
        intent.putExtra(
            "key",
            Film(
                R.drawable.alita,
                "Действие фильма происходит через 300 лет после Великой войны в XXVI веке. Доктор Идо находит останки женщины-киборга. После починки киборг ничего не помнит, но обнаруживает, что в состоянии пользоваться боевыми приемами киборгов. Начинаются поиски утерянных воспоминаний."
            )
        )
        startActivityForResult(intent, 1)
    }

    fun ClickMarvel(view: View?) {
        changeColorMarvel = true
        textFilmMarvel.setTextColor(Color.parseColor("#FFFFFF"))
        OpenThirdFilmActivity()
    }

    private fun OpenThirdFilmActivity() {
        val intent = Intent(this@MainActivity, DescriptionActivity()::class.java)
        intent.putExtra(
            "key",
            Film(
                R.drawable.marvel,
                "Оставшиеся в живых члены команды Мстителей и их союзники должны разработать новый план, который поможет противостоять разрушительным действиям могущественного титана Таноса. После наиболее масштабной и трагической битвы в истории они не могут допустить ошибку."
            )
        )
        startActivityForResult(intent, 1)
    }

    fun ClickT34(view: View?) {
        changeColorT34 = true
        textFilmT34.setTextColor(Color.parseColor("#FFFFFF"))
        OpenFourthFilmActivity()
    }

    private fun OpenFourthFilmActivity() {
        val intent = Intent(this@MainActivity, DescriptionActivity()::class.java)
        intent.putExtra(
            "key",
            Film(
                R.drawable.t34,
                "Во времена величайших испытаний человечества, когда от каждого действия зависят жизни любимых, два заклятых врага начнут свое противостояние. Оказавшись в плену, вчерашний курсант Ивушкин планирует дерзкий побег. Он собирает свой экипаж и бросает вызов немецким танковым ассам во главе с Ягером. Ради своей любви и Родины он готов идти до конца."
            )
        )
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var like: String? = null
        var review: String? = null
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                like = it.getStringExtra("like")
                review = it.getStringExtra("review")
            }
        }
        Log.i(TAG, "Фильм $like - $review")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("KEY_Aladin", changeColorAladin)
        outState.putBoolean("KEY_Alita", changeColorAlita)
        outState.putBoolean("KEY_Marvel", changeColorMarvel)
        outState.putBoolean("KEY_T34", changeColorT34)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean("KEY_Aladin"))
            textFilmAladin.setTextColor(Color.parseColor("#FFFFFF"))
        if (savedInstanceState.getBoolean("KEY_Alita"))
            textFilmAlita.setTextColor(Color.parseColor("#FFFFFF"))
        if (savedInstanceState.getBoolean("KEY_Marvel"))
            textFilmMarvel.setTextColor(Color.parseColor("#FFFFFF"))
        if (savedInstanceState.getBoolean("KEY_T34"))
            textFilmT34.setTextColor(Color.parseColor("#FFFFFF"))
    }

    fun ClickInvite(view: View?) {
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
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}
