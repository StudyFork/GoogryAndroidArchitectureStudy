package com.example.study.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.study.R
import com.example.study.RecyclerAdapter
import com.example.study.data.model.NaverApiData
import com.example.study.data.repository.MovieListRepositoryImpl
import com.example.study.databinding.ActivityMainBinding
import com.example.study.presenter.MovieContract
import com.example.study.presenter.MoviePresenter

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity(), MovieContract.View {
    private lateinit var viewAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    private val moviePresenter: MovieContract.Presenter by lazy {
        MoviePresenter(
            this,
            MovieListRepositoryImpl()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewAdapter = RecyclerAdapter()
        binding.rvMain.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        binding.btnCheck.setOnClickListener {
            moviePresenter.requestMovieList(binding.etvSearch.text.toString())
        }

    }

    override fun showMovieList(naverApiData: List<NaverApiData.Item>) {
        viewAdapter.setItem(naverApiData)
    }

    override fun showErrorResponse(t: Throwable) {
        Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
    }

    override fun showQueryEmpty() {
        Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

}



