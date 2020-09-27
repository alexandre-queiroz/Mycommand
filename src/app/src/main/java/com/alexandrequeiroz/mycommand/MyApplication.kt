package com.alexandrequeiroz.mycommand

import android.app.Application

class MyApplication : Application() {

    var comandaList: List<comanda> =  gerarLista()

}

private fun gerarLista(): List<comanda> {

    val list = ArrayList<comanda>()

    list += comanda("José","R$ 15,00")
    list += comanda("Mariana", "R$ 12,99")
    list += comanda("Carlos", "R$ 32,10")
    list += comanda("Ailton", "R$ 10,00")
    list += comanda("Cidinha", "R$ 57,39")

    return list

}