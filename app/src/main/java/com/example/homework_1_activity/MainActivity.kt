package com.example.homework_1_activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.homework_1_activity.Recycler.NewsItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NewListFragments.OnNewsClickListener,
    NewListFragments.OnNewListSelect, NavigationView.OnNavigationItemSelectedListener {

    private var snackbar: Snackbar? = null
    val listSelectMain = arrayListOf<String>()
    val listSelectFinished = arrayListOf<String>()
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationView()
        snackBar()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, NewListFragments(), NewListFragments.TAG)
            .commit()
    }

    override fun onNewsClick(item: NewsItem) {
        openNewsDetailed(item)
    }

    override fun openNewsSelected(listSelect: String) {
        openNewsSelect(listSelect)
    }

    private fun navigationView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.getHeaderView(0).setBackgroundColor(Color.RED)
    }

    private fun snackBar() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val listenerAdd = View.OnClickListener {
            listSelectFinished.clear()
            if (listSelectMain.size > 0 ) {Toast.makeText(this, " Выбранные фильмы добавлены в избранное", Toast.LENGTH_SHORT).show()}
            else {Toast.makeText(this, " Не выбран ни один из фильмов", Toast.LENGTH_SHORT).show()}
            for (select in 0..listSelectMain.size - 1) listSelectFinished.add(
                listSelectMain[select].toString()
            )
        }
        fab.setOnClickListener { view ->
            snackbar = Snackbar.make(view, "ИЗБРАННОЕ", Snackbar.LENGTH_INDEFINITE)
                .setAction("ДОБАВИТЬ", listenerAdd)
            snackbar!!.setActionTextColor(ContextCompat.getColor(this, R.color.indigo))
            val snackbarView = snackbar!!.view
            snackbarView.setBackgroundColor(Color.GRAY)
            val snCallback: Snackbar.Callback = object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar) {
                }

                override fun onDismissed(snBar: Snackbar, event: Int) {
                }
            }
            snackbar!!.addCallback(snCallback)
            snackbar!!.show()
            snackbar!!.removeCallback(snCallback)
        }

        findViewById<FloatingActionButton>(R.id.button_dismiss).setOnClickListener {
            if (listSelectFinished.size > 0 ){
            snackbar = Snackbar.make(it, "ИЗБРАННОЕ", Snackbar.LENGTH_INDEFINITE)
            Toast.makeText(this, "Список выбранных фильмов был очищен!!!", Toast.LENGTH_SHORT)
                .show()
            snackbar!!.dismiss()
            listSelectFinished.clear()
            listSelectMain.clear()}
        }
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            findViewById<Toolbar>(R.id.toolbar).title = "Список фильмов"
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_home) {
            toolbar?.title = "Список фильмов"
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, NewListFragments(), NewListFragments.TAG)
                .commit()
        } else if (id == R.id.nav_topic) {
            toolbar?.title = "Избранное"
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    NewSelectsFragments.newInstance(listSelectFinished),
                    NewSelectsFragments.TAG
                )
                .addToBackStack(null)
                .commit()
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, " Открываю окно отправки приглашения", Toast.LENGTH_LONG).show()
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

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun openNewsDetailed(item: NewsItem) {

        toolbar?.title = "Детали фильма"
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                NewDetailsFragments.newInstance(item.pictureName, item.description),
                NewDetailsFragments.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    fun openNewsSelect(listSelect: String) {
        Toast.makeText(this, listSelect + " выбран!", Toast.LENGTH_SHORT).show()
        listSelectMain.add(listSelect)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
