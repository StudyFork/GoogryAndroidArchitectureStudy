package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.databinding.ActivityMainBinding
import com.hyper.hyapplication.model.ResultGetSearchMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewAdapter: MovieAdapter
    private val mainViewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity, R.layout.activity_main
        )
        binding.viewModel = mainViewModel

        viewAdapter = MovieAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        viewModelCallBack()
    }

    private fun viewModelCallBack() {
        mainViewModel.message.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                mainViewModel.message.get()?.let { showFailure(it) }
            }
        })

        mainViewModel.movieItem.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                mainViewModel.movieItem.get()?.let { showMovie(it) }
            }
        })
    }


    fun showMovie(item: List<ResultGetSearchMovie.Item>) {
        viewAdapter.resetData(item)
    }

    fun showFailure(it: Throwable) {
        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
    }

    fun showEmptyMessage() {
        Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
    }
}


