package com.example.studyapplication.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.IConn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.MovieList
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment() {
    companion object {
        fun newInstance() : MovieFragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_movie, container, false)
        view.btnSearch.setOnClickListener(btnSearchClickListener())

        return view
    }

    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val movieTitle = etQuery.text.toString()
            requestSearchMovie(movieTitle)
        }
    }

    private fun requestSearchMovie(title : String) {
        Remote.get(ApiClient.getService().getMovieList(title), object : IConn {
            override fun <T> success(result: T) {
                var movieList = result as MovieList

            }

            override fun failed() {
            }
        })
    }
}
