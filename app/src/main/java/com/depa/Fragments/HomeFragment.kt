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
import com.depa.Dialogs.AddFlatFragment
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private lateinit var adapterFlat: FlatAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var service: PlaceHolder
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaFlats= mutableListOf<Flats>()
        listaFlats.add(Flats("A",1,1,"-","-",true,100,1))
        listaFlats.add(Flats("A2",1,1,"-","-",true,100,2))

        val layoutManager=LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recyclerFlats)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        adapterFlat= FlatAdapter(listaFlats)
        recyclerView.adapter=adapterFlat
    }







}