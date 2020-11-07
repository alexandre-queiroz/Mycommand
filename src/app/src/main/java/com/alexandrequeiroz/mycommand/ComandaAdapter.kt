package com.alexandrequeiroz.mycommand

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comanda.view.*

class ComandaAdapter(private val comandaList: List<comanda>) : RecyclerView.Adapter<ComandaAdapter.ComandaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComandaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comanda,
            parent, false)

        return ComandaViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ComandaViewHolder, position: Int) {
        val currentItem = comandaList[position]

        holder.id.text = "#" + currentItem.idComanda
        holder.nome.text = "Mesa: " + currentItem.numMesa
        holder.valor.text = "R$ " + currentItem.valorComanda

    }

    override fun getItemCount() = comandaList.size

    class ComandaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome: TextView = itemView.text_view_1
        val valor: TextView = itemView.text_view_2
        val id: TextView = itemView.textView4

    }

}