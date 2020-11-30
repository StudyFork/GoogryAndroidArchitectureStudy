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
import androidx.databinding.Observable
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityMainBinding
import com.example.hw2_project.recentSearch.RecentSearchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainAdapter = MainRecyclerViewAdapter()

    private val movieRepository = MovieRepositoryImpl()

    private val viewModel = MainViewModel(movieRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mainAdapter

        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.isEmptyQuery.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "Please enter the movie.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.successToGetMovie.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                hideKeyboard()
                viewModel.movieListTest.get()?.let {
                    mainAdapter.updateMovieList(it)
                }
            }
        })
        viewModel.failToGetMovie.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "Fail to get movie.", Toast.LENGTH_SHORT).show()
                Log.e("FailToGetMovie", viewModel.failToGetMovie.get().toString())
            }
        })
        viewModel.startRecentActivity.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val intent = Intent(this@MainActivity, RecentSearchActivity::class.java)
                startActivityForResult(intent, 100)
            }
        })
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
                            viewModel.getMovieFromRepository(clickedMovie)
                        }
                    }
                }
            }
        }
    }

}