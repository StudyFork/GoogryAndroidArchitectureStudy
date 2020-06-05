package com.example.architecture.activity.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.architecture.R
import com.example.architecture.activity.search.adapter.MovieAdapter
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.repository.NaverRepositoryImpl
import com.example.architecture.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {


    private val searchPresenter by lazy {
        val naverRepository = NaverRepositoryImpl(applicationContext)
        SearchPresenter(this, naverRepository)
    }

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.onSearchClick = View.OnClickListener { searchMovie() }
        setRecyclerview()
    }


    private fun searchMovie() {
        val keyword = edt_search_keyword.text.toString()
        searchPresenter.searchMovie(keyword)
    }

    private fun setRecyclerview() {
        recyclerview_search_movieList.adapter = adapter
    }

    override fun showMessageEmptyResult() {
        Toast.makeText(this, getString(R.string.not_found_result), Toast.LENGTH_SHORT).show()
    }

    override fun showMessageEmptyKeyword() {
        Toast.makeText(this, getString(R.string.empty_keyword), Toast.LENGTH_SHORT).show()
    }

    override fun showMovieList(movieList: List<MovieModel>) {
        adapter.addNewItems(movieList)
    }


}
