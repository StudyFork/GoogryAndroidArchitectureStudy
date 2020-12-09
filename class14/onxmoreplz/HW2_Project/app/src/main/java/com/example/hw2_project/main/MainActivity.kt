package com.example.hw2_project.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityMainBinding
import com.example.hw2_project.recentSearch.RecentSearchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val mainAdapter = MainRecyclerViewAdapter()
    private val movieRepository = MovieRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(movieRepository))
            .get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mainAdapter

        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.isEmptyQuery.observe(this) {
            Toast.makeText(this@MainActivity, "Please enter the movie.", Toast.LENGTH_SHORT)
                .show()
        }
        viewModel.successToGetMovie.observe(this) {
            hideKeyboard()
            viewModel.movieList.value?.let {
                mainAdapter.updateMovieList(it)
            }
        }
        viewModel.failToGetMovie.observe(this) {
            Toast.makeText(this@MainActivity, "Fail to get movie.", Toast.LENGTH_SHORT).show()
            Log.e("FailToGetMovie", viewModel.failToGetMovie.value.toString())
        }
        viewModel.startRecentActivity.observe(this) {
            val intent = Intent(this@MainActivity, RecentSearchActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etMovieName.windowToken, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    if (data?.hasExtra("recentMovie")!!) {
                        val clickedMovie: String? = data.getStringExtra("recentMovie")
                        if (clickedMovie != null) {
                            viewModel.query.value = clickedMovie
                            viewModel.getMovieFromRepository()
                        }
                    }
                }
            }
        }
    }

}