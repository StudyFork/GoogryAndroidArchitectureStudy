package com.example.architecturestudy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.databinding.ActivityMainBinding
import com.example.architecturestudy.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private var mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        init()
        subscribeInit()
    }

    private fun init() {
        binding.viewModel = mainViewModel

        movieAdapter = MovieAdapter()
        movieRecyclerView.adapter = movieAdapter
    }

    private fun subscribeInit() {
        with(mainViewModel) {
            movieListObservableField.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    movieListObservableField.get()?.let { showMovieList(it) }
                }
            })
            failMsgObservableField.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    failMsgObservableField.get()?.let { showSearchFailToast(it) }
                }
            })
        }
    }

    fun showMovieList(movieList: List<MovieData>) {
        movieAdapter.setData(movieList)
    }

    fun showSearchFailToast(throwable: Throwable) {
        Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_SHORT).show()
    }
}