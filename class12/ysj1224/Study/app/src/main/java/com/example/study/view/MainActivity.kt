package com.example.study.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.study.R
import com.example.study.RecyclerAdapter
import com.example.study.data.model.NaverApiData
import com.example.study.databinding.ActivityMainBinding
import com.example.study.viewmodel.MovieViewModel

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewAdapter: RecyclerAdapter
    private val viewModel = MovieViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        viewAdapter = RecyclerAdapter()
        init()
        binding.run {
            rvMain.apply {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    fun showMovieList(naverApiData: List<NaverApiData.Item>) {
        viewAdapter.setItem(naverApiData)
    }

    fun showErrorResponse(t: Throwable) {
        Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        with(viewModel) {
            movieList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    movieList.get()?.let { showMovieList(it) }
                }
            })

            fail.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    fail.get()?.let { showErrorResponse(it) }
                }
            })
            noQuery.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}



