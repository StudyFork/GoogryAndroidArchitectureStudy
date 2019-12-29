package com.example.studyapplication.ui.main.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.ui.base.SearchFragment
import com.example.studyapplication.ui.main.movie.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : SearchFragment(R.layout.fragment_movie), MovieContract.View {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var presenter : MovieContract.Presenter
    private val repository: NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = MoviePresenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val movieTitle = etQuery.text.toString()
            presenter.clickSearchButton(movieTitle)
        }
    }

    override fun showList(items: Array<SearchMovieResult.MovieInfo>) {
        movieAdapter.resetItem(items)
    }

    @SuppressLint("ShowToast")
    override fun toastErrorConnFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

}
