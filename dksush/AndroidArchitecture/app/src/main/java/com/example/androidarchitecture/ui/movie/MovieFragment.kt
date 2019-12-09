package com.example.androidarchitecture.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.MovieData
import com.example.androidarchitecture.models.NaverQueryResponse
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
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
            movieAdapter = MovieAdapter()
                .also {
                    recycle.adapter = it
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
            .enqueue(object : Callback<NaverQueryResponse<MovieData>> {
                override fun onFailure(call: Call<NaverQueryResponse<MovieData>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<NaverQueryResponse<MovieData>>,
                    response: Response<NaverQueryResponse<MovieData>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()?.items ?: return
                        movieAdapter.setData(body)

                    }
                }

            })


    }


}
