package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity, R.layout.activity_main
        )
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this


        viewAdapter = MovieAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.message.observe(this, Observer {
            showFailure(it)
        })
    }

    fun showFailure(it: Throwable) {
        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
    }

    fun showEmptyMessage() {
        Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
    }
}


