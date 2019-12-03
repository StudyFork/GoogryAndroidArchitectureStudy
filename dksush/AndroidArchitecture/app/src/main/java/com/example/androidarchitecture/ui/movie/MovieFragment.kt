package com.example.androidarchitecture.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.movie.movieAdapter
import com.example.androidarchitecture.apis.Api
import com.example.androidarchitecture.apis.Api.Companion.base_url
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.MovieData
import com.example.androidarchitecture.models.ResponseMovie
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var mArrData: ArrayList<ResponseMovie>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search.setOnClickListener {
            if (input_text != null) {
                requestMovieList(edit_text.text.toString())

            }
        }
    }

    private fun requestMovieList(text: String) {
        val retrofit = NetworkUtil.getRetrofit(base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java)
        val movieInfo = api.getMovielist(text, 1, 10)
        movieInfo.enqueue(object : retrofit2.Callback<MovieData> {

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful){
                    response.body()?.movies?.let { setList(it) }
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.v("dksush_error", t.toString())

            }
        })

    }


    private fun setList(movie: List<ResponseMovie>) {
        recycle.adapter =
            movieAdapter(movie, activity!!, object : movieAdapter.OnItemClickListener {

                    override fun onItemClick(link: String) {
                        Intent(context, WebviewActivity::class.java).apply {
                            putExtra("link", link)
                        }.run {
                            context?.startActivity(this) }
                    }
                })
        recycle.layoutManager = LinearLayoutManager(activity)
        recycle.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }


}
