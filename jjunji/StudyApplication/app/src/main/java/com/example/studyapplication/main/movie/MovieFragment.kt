package com.example.studyapplication.main.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyapplication.R
import com.example.studyapplication.main.movie.adapter.MovieAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.IConn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.MovieList
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment() {
    lateinit var mContext : Context

    companion object {
        fun newInstance() : MovieFragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        mContext = view.context

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
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
        Remote.get(ApiClient.getService().getMovieList(title), object : IConn {
            override fun <T> success(result: T) {
                val movieList : MovieList? = result as MovieList
                movieList?.let {
                    setAdapter(it)
                }
            }

            override fun failed() {
            }
        })
    }

    // recyclerView 세팅
    private fun setAdapter(response : MovieList) {
        recyclerView.adapter = MovieAdapter(response.arrMovieInfo)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}
