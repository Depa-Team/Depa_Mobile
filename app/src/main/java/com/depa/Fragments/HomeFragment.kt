package com.depa.Fragments

import Beans.Flats
import Interface.PlaceHolder
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depa.Adapters.FlatAdapter
import com.depa.Dialogs.AddFlatFragment
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    lateinit var service: PlaceHolder
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val AddFlatButton: Button =view.findViewById(R.id.addFlatButton)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

        getFlats(view)




        AddFlatButton.setOnClickListener(){
            val showPopUP= AddFlatFragment()
            showPopUP.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")


        }
    }

    fun getFlats(view_:View){
        val preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        service.getFlatsForManager(1).enqueue(object : Callback<List<Flats>>{
            override fun onResponse(call: Call<List<Flats>>, response: Response<List<Flats>>) {
                val flatResponse=response?.body()
                val listaFlats= mutableListOf<Flats>()
                if(flatResponse!=null){
                    for(flat in flatResponse){
                        listaFlats.add(Flats(flat.flatName,flat.managerId,flat.guestId,flat.initialDate,flat.endDate,flat.status,flat.price,flat.id))
                    }

                    val recycler= view_.findViewById<RecyclerView>(R.id.recyclerFlats)
                    val manager = LinearLayoutManager(context)
                    recycler.layoutManager = manager
                    recycler.hasFixedSize()
                    recycler.adapter=FlatAdapter(listaFlats)
                }
            }

            override fun onFailure(call: Call<List<Flats>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        //service.getFlatsForManager(preferences.getString("id","").toString().toInt())
    }


}