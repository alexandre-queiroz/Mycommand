package com.alexandrequeiroz.mycommand

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync
import java.net.URL

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var App = MyApplication()

    val timer = object: CountDownTimer(4000, 1000) {

        override fun onFinish() {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

        override fun onTick(millisUntilFinished: Long) { }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Thread {

            val comandas = getComandas()

            enviaNotificacao(comandas[0])

            runOnUiThread {

                recyclerView.adapter = ComandaAdapter(comandas)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)

            }

        }.start()

        timer.start()
        progressBar.visibility = View.VISIBLE

        configuraMenuLateral()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)

        val search = menu?.findItem(R.id.action_buscar)
        val searchView = search?.actionView as SearchView

        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@HomeActivity, query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == R.id.action_adicionar) {

            val intent = Intent(this, AddActivity::class.java)

            startActivity(intent)
        }

        if (id == R.id.action_atualizar) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            timer.start()
        }

        if (id == R.id.action_config) {

            val intent = Intent(this, ConfigActivity::class.java)

            startActivity(intent)
        }

        if (id == R.id.action_sair) {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral() {

        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_diciplinas -> {
                Toast.makeText(this, "Clicou Disciplinas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true

    }

    fun getComandas(): List<comanda> {

        val TAG = "teste"

        val http = HttpHelper()
        var json = http.get("comandas")

        Log.d(TAG, json)

        return parserJson<List<comanda>>(json)

    }

    fun enviaNotificacao(comanda: comanda) {
    // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, HomeActivity::class.java)
    // Disparar notificação
        NotificationUtil.create(this, 1, intent, "Mycommand", "Novas comandas")
    }

}

