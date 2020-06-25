package com.yuchen.publisher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.yuchen.publisher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        this.supportActionBar?.hide()

        val application = requireNotNull(this).application
        val viewModelFactory = MainViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        binding.addData.setOnClickListener {
            Log.d("chenyjzn", "Add click")
            findNavController(R.id.nav_fragment).navigate(NavigationDirections.actionGlobalPublishDialog())
        }
    }
}
