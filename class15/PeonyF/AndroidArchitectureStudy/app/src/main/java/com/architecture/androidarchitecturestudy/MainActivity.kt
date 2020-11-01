package com.architecture.androidarchitecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.architecture.androidarchitecturestudy.model.MovieResponse
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val callList = mutableListOf<Call<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun searchMovie(view: View) {
        val query = etQuery.text.toString()
        val call = ApiClient.NETWORK_SERVICE.getMovie(query)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                tvOutput.text = "에러 발생"
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val body = response.body()
                if(body != null && response.isSuccessful){
                    tvOutput.text = Gson().toJson(body)
                }
            }
        })
        callList.add(call)
    }

    override fun onDestroy() {
        callList.forEach(it.cancel())
        super.onDestroy()
    }
}
