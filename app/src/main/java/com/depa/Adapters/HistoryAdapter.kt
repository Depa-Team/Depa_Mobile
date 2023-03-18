package com.depa.Adapters

import Beans.History
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depa.R
import com.depa.ViewHolders.HistoryViewHolder

class HistoryAdapter(
    val listaHistory: List<History>,
    val listener: HistoryAdapterListener
    ): RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return HistoryViewHolder(layoutInflater.inflate(R.layout.item_flat_history,parent,false))
    }

    override fun getItemCount(): Int=listaHistory.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item=listaHistory[position]
        holder.render(item)
        holder.btnDelete_history.setOnClickListener(){
            listener.onDeleteItemClick(item)
        }
    }

}