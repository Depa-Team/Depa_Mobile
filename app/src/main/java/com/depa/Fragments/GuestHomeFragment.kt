package com.depa.Fragments

import Beans.Flats
import Beans.Usuarios
import Interface.PlaceHolder
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GuestHomeFragment : Fragment() {
    lateinit var service:PlaceHolder
    private var progress=10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progress_bar: ProgressBar=view.findViewById(R.id.progress_bar)
        val text_view_progress: TextView=view.findViewById(R.id.text_view_progress)
        val greetings_guest_home: TextView=view.findViewById(R.id.greetings_guest_home)
        val txtCaseroCall: TextView=view.findViewById(R.id.txtCaseroCall)
        val txtMensualidad: TextView=view.findViewById(R.id.txtMensualidad)



        val preferences = this.requireActivity().getSharedPreferences("myCurrentSesion", Context.MODE_PRIVATE)

        greetings_guest_home.setText("Hola, "+preferences.getString("name","")+" !")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

        service.getCaseroForGuest(2).enqueue(object :Callback<List<Flats>>{
            override fun onResponse(call: Call<List<Flats>>, response: Response<List<Flats>>) {
                if(response.body()!=null){
                    for (item in response.body()!!){
                        service.getUser(item.managerId.toString().toInt()).enqueue(object : Callback<Usuarios> {
                            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                                val user=response?.body()
                                if (user!=null){
                                    txtCaseroCall.append(user.name)
                                }
                            }

                            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                        txtMensualidad.append(item.price.toString()+ " S/.")
                    }
                }
            }

            override fun onFailure(call: Call<List<Flats>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        /*service.getCaseroForGuest(2).enqueue(object :Callback<Flats>{
            override fun onResponse(call: Call<Flats>, response: Response<Flats>) {
                val flat_=response.body()
                if (flat_ != null) {
                    service.getUser(flat_.managerId.toString().toInt()).enqueue(object :Callback<Usuarios>{
                        override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                            txtCaseroCall.append(response.body()?.name.toString())
                        }

                        override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                            t.suppressedExceptions
                        }

                    })
                }
            }

            override fun onFailure(call: Call<Flats>, t: Throwable) {
                t.suppressedExceptions
            }

        })*/


        progress_bar.progress=progress
        text_view_progress.setText(" Te quedan \n    30 dias")
    }
}