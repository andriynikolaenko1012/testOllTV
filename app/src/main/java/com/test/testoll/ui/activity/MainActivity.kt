package com.test.testoll.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.test.testoll.R
import com.test.testoll.databinding.ActivityMainBinding
import com.test.testoll.ext.visibleOrGone

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val navController = findNavController(R.id.navHostFragment)
            navView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.televisionFragment ->  navView.visibleOrGone(false)
                    else -> navView.visibleOrGone(true)
                }
            }

            navView.setOnItemReselectedListener {  }
        }
    }

}