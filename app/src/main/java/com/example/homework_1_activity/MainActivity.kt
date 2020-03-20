package com.example.homework_1_activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.homework_1_activity.Recycler.NewsItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), NewListFragment.OnNewsClickListener,
    NewListFragment.OnNewListSelect, NavigationView.OnNavigationItemSelectedListener,
    NewSelectFragment.TransferData {

    private var snackbar: Snackbar? = null
    val listSelectFinished = arrayListOf<String>()
    var toolbar: Toolbar? = null
    var menuDrawer: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView()
        snackBar()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, NewListFragment(), NewListFragment.TAG)
            .commit()


    }

    override fun onNewsClick(item: NewsItem) {
        openNewsDetailed(item)
    }

    override fun openNewsSelected(listSelect: String) {
        openNewsSelect(listSelect)
    }

    override fun transferData(position: Int) {
        transferDataActivity(position)
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
        menuDrawer = navigationView.menu
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.getHeaderView(0).setBackgroundColor(Color.RED)
    }

    private fun snackBar() {
        findViewById<FloatingActionButton>(R.id.button_dismiss).setOnClickListener {
            if (listSelectFinished.size > 0) {
                snackbar = Snackbar.make(it, "ИЗБРАННОЕ", Snackbar.LENGTH_INDEFINITE)
                Toast.makeText(this, "Список выбранных фильмов был очищен!!!", Toast.LENGTH_SHORT)
                    .show()
                snackbar!!.dismiss()
                listSelectFinished.clear()
            }
        }
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            findViewById<Toolbar>(R.id.toolbar).title = "Список фильмов"
            clearMenu()
            menuDrawer?.getItem(0)?.setChecked(true)
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        menuDrawer?.getItem(0)?.setChecked(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        clearMenu()
        if (id == R.id.nav_home) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                onBackPressed()
            }
        } else if (id == R.id.nav_topic) {
            toolbar?.title = "Избранное"
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    NewSelectFragment.newInstance(listSelectFinished),
                    NewSelectFragment.TAG
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
        item.setChecked(true)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun clearMenu(){
        for (i in 0..menuDrawer?.size()!!-1) {
            menuDrawer?.getItem(i)?.setChecked(false)
        }
    }

    fun openNewsDetailed(item: NewsItem) {
        toolbar?.title = "Детали фильма"
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                NewDetailedFragment.newInstance(item.pictureName, item.description),
                NewDetailedFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    fun openNewsSelect(listSelect: String) {
        Toast.makeText(this, listSelect + " добавлен с избранное!", Toast.LENGTH_SHORT).show()
        listSelectFinished.add(listSelect)
    }

    fun transferDataActivity(position: Int) {
        listSelectFinished.removeAt(position)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
