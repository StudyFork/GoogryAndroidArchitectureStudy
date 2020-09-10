package com.camai.archtecherstudy.data.source.remote

import android.util.Log
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.model.MovieResponseModel
import com.camai.archtecherstudy.data.network.MovieApiServiceImpl
import retrofit2.Call
import retrofit2.Response

object MovieRemoteDataSourceImpl :
    MovieRemoteDataSource {

    private val TAG = "MovieRemoteDataSource"

    override fun getSearchMovie(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        MovieApiServiceImpl.create().getMovieSearch(keyword, display, start).enqueue(object :
            retrofit2.Callback<MovieResponseModel> {

            override fun onResponse(
                call: Call<MovieResponseModel>,
                response: Response<MovieResponseModel>
            ) {
                // Success
                if (response.isSuccessful) {

                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }

                } else {
                    Log.e(TAG, response.message())
                    failed(response.message())
                }

            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                // Failed
                Log.e(TAG, t.message.toString())
                failed(t.message.toString())

            }
        })
    }
}