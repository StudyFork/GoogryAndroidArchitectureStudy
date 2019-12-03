package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Movie
import com.jay.architecturestudy.model.ResponseNaverQuery
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieFragment : BaseFragment() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragemnt_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter(activity)
                .also {
                    recycler_view.run {
                        adapter = it
                        layoutManager = LinearLayoutManager(activity)
                        addItemDecoration(
                            DividerItemDecoration(
                                activity,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                }
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