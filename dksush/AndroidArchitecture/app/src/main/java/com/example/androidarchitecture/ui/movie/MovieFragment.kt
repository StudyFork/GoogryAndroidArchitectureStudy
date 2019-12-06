package com.example.androidarchitecture.ui.movie


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.MovieData
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            movieAdapter = MovieAdapter(it)
                .also {
                    recycle.adapter = it
                    recycle.layoutManager = LinearLayoutManager(activity)
                    recycle.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
        }



        btn_search.setOnClickListener {
            if (edit_text != null) {
                requestMovieList(edit_text.text.toString())

            }
        }
    }

    private fun requestMovieList(text: String) {
        NetworkUtil.apiService.getMovieList(text, 1, 10)
            .enqueue(object : retrofit2.Callback<MovieData> {
                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.v("dksush_error", t.toString())
                }

                override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.movies?: return
                        movieAdapter.setData(body)

                    }
                }

            })

    }


}
