package com.depa.Fragments

import Beans.History
import Interface.PlaceHolder
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depa.Adapters.HistoryAdapter
import com.depa.Adapters.HistoryAdapterListener
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class HistoryFragment : Fragment(),HistoryAdapterListener {
    private lateinit var adapterHistory: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var service: PlaceHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deleteHistoryButton: Button=view.findViewById(R.id.deleteHistoryButton)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

        val preferences = this.requireActivity().getSharedPreferences("myCurrentSesion", Context.MODE_PRIVATE)
        service.getHistoryForManager(preferences.getString("id","").toString().toInt()).enqueue(object :Callback<List<History>>{
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                val historyResponse=response.body()
                val listaHistory= mutableListOf<History>()
                if(historyResponse!=null){
                    for(item in historyResponse){
                        listaHistory.add(History(
                                item.id,
                                item.flatName,
                                item.managerId,
                                item.guestName,
                                item.initialDate,
                                item.endDate,
                                item.price))
                    }
                    val layoutManager=LinearLayoutManager(context)
                    recyclerView=view.findViewById(R.id.recyclerHistory)
                    recyclerView.layoutManager=layoutManager
                    recyclerView.setHasFixedSize(true)
                    adapterHistory= HistoryAdapter(listaHistory,this@HistoryFragment)
                    recyclerView.adapter=adapterHistory
                }
            }

            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        deleteHistoryButton.setOnClickListener(){
            Toast.makeText(context,"Historial borrado",Toast.LENGTH_SHORT).show()
        }

    }
    override fun onDeleteItemClick(history: History) {
        Toast.makeText(context,"Eliminando: "+history.flatName,Toast.LENGTH_SHORT).show()
    }

}