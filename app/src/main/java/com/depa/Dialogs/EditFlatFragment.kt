package com.depa.Dialogs

import Beans.Flats
import Beans.Usuarios
import Interface.PlaceHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditFlatFragment(flat: Flats) : DialogFragment() {

    val flat_=flat
    lateinit var service: PlaceHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_flat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtEditInquilinoName: TextView=view.findViewById(R.id.txtEditInquilinoName)
        val txtFlat_id_edit_name: EditText=view.findViewById(R.id.txtFlat_id_edit_name)
        val txtFlat_id_edit_cost: EditText=view.findViewById(R.id.txtFlat_id_edit_cost)

        if(flat_.guestId.toString().toInt()!=0){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

            println("el codigo:"+flat_.guestId.toString())
            service.getUser(flat_.guestId.toString().toInt()).enqueue(object :Callback<Usuarios>{
                override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                    val user=response?.body()
                    if (user!=null){
                        txtEditInquilinoName.setText(user.name)
                    }
                }

                override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        txtFlat_id_edit_name.setText(flat_.flatName)
        txtFlat_id_edit_cost.setText(flat_.price.toString())

        val editFlatDialogButton=view.findViewById<Button>(R.id.editFlatDialogButton)
        editFlatDialogButton.setOnClickListener(){
            dismiss()
        }

    }


}