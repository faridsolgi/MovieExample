package com.fsol.movieexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fsol.movieexample.databinding.ActivityMainBinding
import com.fsol.movieexample.model.Utils.navHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNavigateUp(): Boolean {
        return navHost(binding.root).navController.navigateUp() || super.onNavigateUp()
    }
}