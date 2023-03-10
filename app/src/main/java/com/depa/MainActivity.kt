package com.depa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

        val settingsActions: ImageView=findViewById(R.id.logOutButton)
        toolbar=findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)
        drawerLayout=findViewById(R.id.drawer)
        navigationView=findViewById(R.id.navigationView)

        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController=navHostFragment.navController

        appBarConfiguration= AppBarConfiguration(setOf(R.id.id_home_fragment,R.id.id_profile_fragment,R.id.id_settings_fragment),drawerLayout)
        setupActionBarWithNavController(navController,drawerLayout)
        navigationView.setupWithNavController(navController)

        settingsActions.setOnClickListener(){
            Toast.makeText(this,"Sesion cerrada",Toast.LENGTH_SHORT).show()
        }

    }



    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}