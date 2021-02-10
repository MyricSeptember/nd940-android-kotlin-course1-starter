package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.welcomeFragment
            )
        )

        setSupportActionBar(activityMainBinding.toolbar)
        navController = findNavController(R.id.nav_host_Fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }
}
