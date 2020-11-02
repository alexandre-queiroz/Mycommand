package com.alexandrequeiroz.lmsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        lbl_login.setText(R.string.mensagem_login)

        btn_login.setOnClickListener {

            var user = txt_login_user.text.toString()
            var senha = txt_login_senha.text.toString()

            if(user == "Junin" && senha == "123" ) {

                val home = Intent(this, HomeActivity::class.java)

                startActivity(home)

                Toast.makeText(this, "user: " + user + ", tá logado meu bom", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this, "Login inválido", Toast.LENGTH_LONG).show()
            }

        }

    }

}
