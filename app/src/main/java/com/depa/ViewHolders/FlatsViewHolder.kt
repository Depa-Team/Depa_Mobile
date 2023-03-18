package com.depa.ViewHolders

import Beans.Flats
import Beans.Usuarios
import Interface.PlaceHolder
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.red
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlatsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    lateinit var service: PlaceHolder

    val flat_card_name=view.findViewById<TextView>(R.id.flat_card_name)
    val flat_card_label=view.findViewById<TextView>(R.id.flat_card_label)
    val guest_card_name=view.findViewById<TextView>(R.id.guest_card_name)
    val payment_card_value=view.findViewById<TextView>(R.id.payment_card_value)
    val btn_acciones=view.findViewById<Button>(R.id.btnAcciones)
    val btn_call_guest=view.findViewById<ImageView>(R.id.btn_call_guest)


    fun render(flatModel: Flats){
        if(flatModel.status==true){
            flat_card_label.text = "Disponible"
            btn_acciones.setText("Agregar")
            btn_call_guest.isVisible=false
        }
        else{
            flat_card_label.text = "Ocupado"
        }
        flat_card_name.text=flatModel.flatName

        if(flatModel.guestId!=0){
            payment_card_value.append(flatModel.price.toString()+" S/.")
            val retrofit = Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)
            service.getUser(flatModel.guestId.toString().toInt()).enqueue(object : Callback<Usuarios> {
                override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                    val user=response?.body()
                    if (user!=null){
                        guest_card_name.append(user.name)
                    }
                }

                override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                    t.suppressedExceptions
                }
            })
        }

    }
}