package com.depa.Fragments

import Beans.Flats
import Interface.PlaceHolder
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depa.Adapters.FlatAdapter
import com.depa.Adapters.FlatAdapterListener
import com.depa.Dialogs.AddFlatFragment
import com.depa.Dialogs.EditFlatFragment
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(),FlatAdapterListener {

    private lateinit var adapterFlat: FlatAdapter
    private lateinit var recyclerView: RecyclerView
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



        val preferences = this.requireActivity().getSharedPreferences("myCurrentSesion", Context.MODE_PRIVATE)
        service.getFlatsForManager(preferences.getString("id","").toString().toInt()).enqueue(object : Callback<List<Flats>>{
            override fun onResponse(call: Call<List<Flats>>, response: Response<List<Flats>>) {
                val flatResponse=response.body()
                val listaFlats= mutableListOf<Flats>()
                if(flatResponse!=null){
                    for(flat in flatResponse){
                        //Toast.makeText(context,flat.flatName.toString(),Toast.LENGTH_SHORT).show()
                        listaFlats.add(Flats(flat.flatName,flat.managerId,0,"-","-",true,100,flat.id.toString().toInt()))
                    }
                    val layoutManager=LinearLayoutManager(context)
                    recyclerView=view.findViewById(R.id.recyclerFlats)
                    recyclerView.layoutManager=layoutManager
                    recyclerView.setHasFixedSize(true)
                    adapterFlat= FlatAdapter(listaFlats,this@HomeFragment)
                    recyclerView.adapter=adapterFlat
                }
            }

            override fun onFailure(call: Call<List<Flats>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        AddFlatButton.setOnClickListener(){
            val showPopUP= AddFlatFragment()
            showPopUP.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")


        }


    }

    override fun onActionsItemClick(flat: Flats) {
        val showPopUP= EditFlatFragment(flat)
        showPopUP.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")

    }


}