package com.depa.Fragments

import Beans.Flats
import Beans.Usuarios
import Interface.PlaceHolder
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.depa.DateCalculator.Calculator
import com.depa.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import kotlin.math.truncate

class GuestHomeFragment : Fragment() {
    lateinit var service:PlaceHolder
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
        val id_advertisement: TextView=view.findViewById(R.id.id_advertisement)
        val txtContractAdvertisement: TextView=view.findViewById(R.id.txtContractAdvertisement)
        val btn_call_manager: ImageView=view.findViewById(R.id.btn_call_manager)
        var managerId: Int=0



        val preferences = this.requireActivity().getSharedPreferences("myCurrentSesion", Context.MODE_PRIVATE)

        greetings_guest_home.setText("Hola, "+preferences.getString("name","")+" !")


        //14 de febrero 2023
        id_advertisement.setText(getDeathLineDateForMontlyPayment())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

        service.getCaseroForGuest(preferences.getString("id","").toString().toInt()).enqueue(object :Callback<List<Flats>>{
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
                                t.suppressedExceptions
                            }
                        })
                        txtMensualidad.append(item.price.toString()+ " S/.")
                        txtContractAdvertisement.setText(getDeathLineForContract(item.endDate.toString()))
                        managerId=item.managerId
                    }
                }
            }

            override fun onFailure(call: Call<List<Flats>>, t: Throwable) {
                t.suppressedExceptions
            }

        })

        progress_bar.progress=getDayPorcentageLeft()
        text_view_progress.setText(" Te quedan \n    "+getDayLeft()+" dias")
        btn_call_manager.setOnClickListener(){
            service.getUser(managerId.toString().toInt()).enqueue(object :Callback<Usuarios>{
                override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                    val usu=response.body()
                    if(usu!=null){
                        val intent: Intent = Intent(Intent.ACTION_DIAL)
                        intent.setData(Uri.parse("tel:"+usu.phoneNumber.toString()))
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                    t.suppressedExceptions
                }

            })
        }
    }
    fun getDeathLineDateForMontlyPayment():String{
        val calc= Calculator()
        var datePhrase="Tu proximo pago vence el "

        val dateFormatMonth = SimpleDateFormat("MMM").format(Date())
        val dateFormatYear = SimpleDateFormat("yyyy").format(Date())

        datePhrase+=calc.deathLineDateForMontlyPayment+" "+dateFormatYear
        return datePhrase.toString()
    }
    fun getDayLeft():Int{
        val calc= Calculator()
        val dateFormatday = SimpleDateFormat("d").format(Date())
        return calc.getDayLeft(dateFormatday.toInt())
    }

    fun getDayPorcentageLeft(): Int {
        val calc= Calculator()
        val dateFormatday = SimpleDateFormat("d").format(Date())
        return calc.getDayPorcentageLeft(dateFormatday.toInt()).toInt()
    }

    fun getDeathLineForContract(endDate: String): String{
        val calc= Calculator()
        var datePhrase="Tu contrato termina el "
        val delim = "/"
        //val day: Int;val month:Int;val year:Int;
        val list = endDate.split(delim)
        datePhrase+=calc.getDeathLineForContract(list[0].toInt(),list[1].toInt())+" "+ list[2].toInt()
        return datePhrase

    }
}