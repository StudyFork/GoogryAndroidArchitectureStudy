package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        mainViewModel.emptyData.observe(this, Observer {
            showEmptyMessage("Empty")
        })
    }

    private fun showFailure(it: Throwable) {
        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
    }
    private fun showEmptyMessage(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }
}


