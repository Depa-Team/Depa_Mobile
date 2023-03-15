package com.depa

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController:NavController
    private lateinit var navHostFragment:NavHostFragment
    private lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logOutButton:ImageView=findViewById(R.id.logOutButton)


        toolbar=findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)
        drawerLayout=findViewById(R.id.drawer)
        navigationView=findViewById(R.id.navigationView)


        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

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
        userType.setText("Administrador")
        userName.setText(sharedPrefCurrentSesion.getString("name",""))

        val navController=findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}