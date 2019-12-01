package com.jay.architecturestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.ResponseMovies
import com.jay.architecturestudy.network.Api
import kotlinx.android.synthetic.main.fragemnt_movie.*
import kotlinx.android.synthetic.main.view_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieFragment : Fragment() {

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
        search_btn.setOnClickListener {
            val keyword = search_editor.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(activity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                search(keyword)
            }
        }
        activity?.let { activity ->
            movieAdapter = MovieAdapter(activity)
                .also {
                    recycler_view.apply {
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

    private fun search(keyword: String) {
        Api.getMovies(keyword)
            .enqueue(object : Callback<ResponseMovies> {
                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseMovies>,
                    response: Response<ResponseMovies>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        movieAdapter.setData(body.movies)
                    }
                }

            })
    }
}