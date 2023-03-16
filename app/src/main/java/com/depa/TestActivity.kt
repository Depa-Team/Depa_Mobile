package com.depa

import Beans.Flats
import Interface.PlaceHolder
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depa.Adapters.FlatAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestActivity : AppCompatActivity() {

    lateinit var service: PlaceHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)
    }


}