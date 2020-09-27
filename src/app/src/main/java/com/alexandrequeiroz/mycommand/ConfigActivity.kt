package com.alexandrequeiroz.mycommand

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val toolbar = supportActionBar

        toolbar!!.title = "Configurações"

        toolbar!!.setDisplayHomeAsUpEnabled (true)

    }
}