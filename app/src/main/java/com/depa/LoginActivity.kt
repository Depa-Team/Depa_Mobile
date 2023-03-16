package com.depa

import Beans.Usuarios
import Interface.PlaceHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val checkBoxCredenciales: CheckBox=findViewById(R.id.checkboxCredenciales)
        val signInRedirectButton: TextView=findViewById(R.id.signInRedirectButton)

        val sharedPref=getSharedPreferences("myPref", MODE_PRIVATE)
        val sharedPrefCurrentSesion=getSharedPreferences("myCurrentSesion", MODE_PRIVATE)



        val check: Boolean=sharedPref.getString("val","false").toBoolean()

        if (check){
            userEmail.setText(sharedPref.getString("email","").toString())
            userPassword.setText(sharedPref.getString("password","").toString())
            checkBoxCredenciales.isChecked=true
        }else{
            userEmail.setText(null)
            userPassword.setText(null)
            checkBoxCredenciales.isChecked=false
        }

        val intent= Intent(this, MainActivity::class.java)
        val intentGuest=Intent(this,MainGuestActivity::class.java)
        //val test=Intent(this, TestActivity::class.java)


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
                                    val editor=sharedPref.edit()
                                    val editorCurrentUser=sharedPrefCurrentSesion.edit()
                                    editorCurrentUser.putString("email",user.email)
                                    editorCurrentUser.putString("password",user.password)
                                    editorCurrentUser.putString("name",user.name)
                                    editorCurrentUser.putString("type",user.type)
                                    editorCurrentUser.putString("plan",user.plan)
                                    editorCurrentUser.putString("id", user.id.toString())
                                    editorCurrentUser.commit()
                                    if (checkBoxCredenciales.isChecked){
                                        editor.putString("val","true")
                                        editor.putString("email",userEmail.text.toString())
                                        editor.putString("password",userPassword.text.toString())
                                    }
                                    else{
                                        editor.putString("val","")
                                        editor.putString("email","")
                                        editor.putString("password","")
                                    }
                                    editor.commit()
                                    if(user.type=="manager"){
                                        startActivity(intent)
                                        //Toast.makeText(this@LoginActivity,user.type.toString(),Toast.LENGTH_SHORT).show()
                                        return
                                    }else{
                                        startActivity(intentGuest)
                                        //Toast.makeText(this@LoginActivity,user.type.toString(),Toast.LENGTH_SHORT).show()
                                        return
                                    }

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