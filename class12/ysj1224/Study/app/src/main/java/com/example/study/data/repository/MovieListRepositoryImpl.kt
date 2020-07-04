package com.example.study.data.repository

import android.widget.Toast
import com.example.study.data.api.NaverRetrofit
import com.example.study.data.model.NaverApiData
import com.example.study.view.clientId
import com.example.study.view.clientSecret
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListRepositoryImpl:MovieListRepository {
    override fun doSearch(query: String): List<NaverApiData.Item> {
            NaverRetrofit.SERVICE.getSearch(
                clientId = clientId,
                clientPw = clientSecret,
                query = query
            ).enqueue(object : Callback<NaverApiData> {
                override fun onFailure(call: Call<NaverApiData>, t: Throwable) {

                }

                override fun onResponse(call: Call<NaverApiData>, response: Response<NaverApiData>){
                    if (response.isSuccessful) {

                        response.body()?.let {

                        }
                    } else {
//                        Toast.makeText(this@MainActivity, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }
}