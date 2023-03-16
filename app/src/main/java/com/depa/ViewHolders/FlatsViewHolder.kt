package com.depa.ViewHolders

import Beans.Flats
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.depa.R

class FlatsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val flat_card_name=view.findViewById<TextView>(R.id.flat_card_name)
    val flat_card_label=view.findViewById<TextView>(R.id.flat_card_label)
    val guest_card_name=view.findViewById<TextView>(R.id.guest_card_name)
    val payment_card_value=view.findViewById<TextView>(R.id.payment_card_value)

    fun render(flatModel: Flats){
        if(flatModel.status==true){
            flat_card_label.text = "Disponible"
        }
        else{
            flat_card_label.text = "Ocupado"
        }
        flat_card_name.text=flatModel.flatName
        guest_card_name.text=flatModel.guestId.toString()
        payment_card_value.text=flatModel.price.toString()
    }
}