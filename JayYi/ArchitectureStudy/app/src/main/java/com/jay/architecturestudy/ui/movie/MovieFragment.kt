package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Movie
import com.jay.architecturestudy.model.ResponseNaverQuery
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieFragment() : BaseFragment(R.layout.fragemnt_movie) {

    private lateinit var movieAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter()
            recycler_view.run {
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        Api.getMovies(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Movie>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Movie>>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Movie>>,
                    response: Response<ResponseNaverQuery<Movie>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        movieAdapter.setData(body.items)
                    }
                }

            })
    }
}