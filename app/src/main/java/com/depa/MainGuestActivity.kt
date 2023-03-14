package com.depa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainGuestActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController:NavController
    private lateinit var navHostFragment:NavHostFragment
    private lateinit var appBarConfiguration:AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_guest)

        val logOutButton:ImageView=findViewById(R.id.logOutButton)

        toolbar=findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)
        drawerLayout=findViewById(R.id.drawerGuest)
        navigationView=findViewById(R.id.navigationGuestView)


        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerGuestView) as NavHostFragment

        navController=navHostFragment.navController

        appBarConfiguration= AppBarConfiguration(setOf(R.id.id_home_fragment,R.id.id_history_fragment,R.id.id_settings_fragment,R.id.id_guest_home_fragment,R.id.id_profile_fragment),drawerLayout)
        setupActionBarWithNavController(navController,drawerLayout)
        navigationView.setupWithNavController(navController)




        logOutButton.setOnClickListener(){
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val sharedPrefCurrentSesion=getSharedPreferences("myCurrentSesion", MODE_PRIVATE)

        val userType:TextView=findViewById(R.id.typeUser)
        val userName:TextView=findViewById(R.id.channelName)
        userType.setText("Inquilino")
        userName.setText(sharedPrefCurrentSesion.getString("name",""))


        val navController=findNavController(R.id.fragmentContainerGuestView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}