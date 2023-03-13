package com.depa

import Beans.Post
import Beans.Usuarios
import Interface.PlaceHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    lateinit var service:PlaceHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Depa-Team/Depa-json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create<PlaceHolder>(PlaceHolder::class.java)

        val userEmail:EditText=findViewById(R.id.userEmail)
        val userPassword:EditText=findViewById(R.id.userPassword)

        val logInButton: Button=findViewById(R.id.logInButton)
        val signInRedirectButton: TextView=findViewById(R.id.signInRedirectButton)

        val intent= Intent(this, MainActivity::class.java)


        logInButton.setOnClickListener(){

            if(userEmail.text.toString()!="" && userPassword.text.toString()!=""){
                service.getUsers().enqueue(object: Callback<List<Usuarios>>{
                    override fun onResponse(
                        call: Call<List<Usuarios>>,
                        response: Response<List<Usuarios>>
                    ) {
                        val UserList=response?.body()
                        if(UserList!=null){
                            for(user in UserList){
                                if(userEmail.text.toString() == user.email && userPassword.text.toString()==user.password){
                                    Toast.makeText(this@LoginActivity,user.type.toString(),Toast.LENGTH_SHORT).show()
                                    startActivity(intent)
                                    return
                                }
                            }
                            Toast.makeText(this@LoginActivity,"Usuario o contraseña incorrectas",Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<List<Usuarios>>, t: Throwable) {
                        t?.printStackTrace()
                    }

                })
            }else{
                Toast.makeText(this@LoginActivity,"Campos vacios",Toast.LENGTH_SHORT).show()
            }


        }
        signInRedirectButton.setOnClickListener(){
            val intent= Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

}