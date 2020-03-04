package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieRecyclerViewAdpater
    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = MovieRecyclerViewAdpater()

        binding.vm = vm
        binding.lifecycleOwner = this
        binding.rvMovieList.adapter = adapter
        observableProperty()

        vm.getRecentData()

    }

    private fun observableProperty() {

        vm.errorQueryBlank.observe(this@MainActivity, Observer {
            showToast(getString(R.string.query_none))
        })

        vm.errorFailSearch.observe(this@MainActivity, Observer {
            if (vm.resultEmpty.value as Boolean) {
                showToast(getString(R.string.movie_search_fail))
            }
        })
        vm.resultEmpty.observe(this@MainActivity, Observer {
            if (vm.resultEmpty.value as Boolean) {
                showToast(getString(R.string.result_none))
            }
        })

    }

    private fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

}
