package com.depa.Adapters

import Beans.Flats
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.depa.Dialogs.AddFlatFragment
import com.depa.Dialogs.EditFlatFragment
import com.depa.R
import com.depa.ViewHolders.FlatsViewHolder

class FlatAdapter(
    val listaFlat:List<Flats>,
    val listener: FlatAdapterListener

    ):RecyclerView.Adapter<FlatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatsViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return FlatsViewHolder(layoutInflater.inflate(R.layout.item_flat,parent,false))
    }

    override fun getItemCount(): Int=listaFlat.size

    override fun onBindViewHolder(holder: FlatsViewHolder, position: Int) {
        val item=listaFlat[position]
        holder.render(item)
        holder.btn_acciones.setOnClickListener(){
            listener.onActionsItemClick(item)
        }
    }

}