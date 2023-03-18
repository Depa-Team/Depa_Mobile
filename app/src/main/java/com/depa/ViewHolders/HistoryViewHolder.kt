package com.depa.ViewHolders

import Beans.History
import Interface.PlaceHolder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.depa.R

class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    lateinit var service: PlaceHolder
    val flat_card_history_name=view.findViewById<TextView>(R.id.flat_card_history_name)
    val guest_card_history_name=view.findViewById<TextView>(R.id.guest_card_history_name)
    val payment_card_history_value=view.findViewById<TextView>(R.id.payment_card_history_value)
    val date_card_history_start=view.findViewById<TextView>(R.id.date_card_history_start)
    val date_card_history_end=view.findViewById<TextView>(R.id.date_card_history_end)
    val btnDelete_history=view.findViewById<Button>(R.id.btnDelete_history)

    fun render(historyModel: History){
        flat_card_history_name.append(historyModel.flatName)
        guest_card_history_name.append(historyModel.guestName)
        payment_card_history_value.append(historyModel.price.toString())
        date_card_history_start.append(historyModel.initialDate)
        date_card_history_end.append(historyModel.endDate)

    }


}