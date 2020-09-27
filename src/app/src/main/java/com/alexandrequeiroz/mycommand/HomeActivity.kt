package com.alexandrequeiroz.mycommand

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

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

        recyclerView.adapter = ComandaAdapter(App.comandaList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        timer.start()
        progressBar.visibility = View.VISIBLE

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

}

