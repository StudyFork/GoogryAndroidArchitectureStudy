package com.example.studyapplication.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.main.movie.adapter.MovieAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.data.model.MovieList
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val movieTitle = etQuery.text.toString()
            requestSearchMovie(movieTitle)
        }
    }

    // 영화 검색 요청
    private fun requestSearchMovie(title : String) {
        Remote.get(ApiClient.getService().getMovieList(title), object : Conn {
            override fun <T> success(result: T) {
                val movieList : MovieList? = result as MovieList
                movieList?.let {
                    movieAdapter.resetItem(movieList.arrMovieInfo)
                }
            }

            override fun failed() {
            }
        })
    }

    companion object {
        fun newInstance() : MovieFragment {
            return MovieFragment()
        }
    }
}
