package com.example.studyapplication.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.main.movie.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieContract.View {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var presenter : MovieContract.UserActions
    private val repository: NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

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

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

}
