package com.alexandrequeiroz.mycommand

import android.app.ActionBar
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastrar.*

class AddActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        var App = MyApplication()

        btnCadastrar.setOnClickListener {
            App.comandaList += comanda(novaComandaNome.text.toString(),
                novaComandaValor.text.toString())

            Toast.makeText(this, "Nova comanda cadastrada", Toast.LENGTH_LONG).show()

        }

        val toolbar = supportActionBar

        toolbar!!.title = "Adicionar"

        toolbar!!.setDisplayHomeAsUpEnabled (true)

    }
}