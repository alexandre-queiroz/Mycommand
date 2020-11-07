package com.alexandrequeiroz.mycommand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {

            val user = txtUser.text.toString()
            val senha = txtSenha.text.toString()

            if (user == "aluno" && senha == "impacta"){

                val intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
                finish()

            } else { Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show() }

        }

    }

    fun RealizarLogin(): String {

        val TAG = "teste"

        val http = HttpHelper()
        var json = http.get("login")

        Log.d(TAG, json)

        return ""

    }

}