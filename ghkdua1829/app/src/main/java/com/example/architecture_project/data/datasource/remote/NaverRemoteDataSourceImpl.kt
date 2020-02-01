package com.example.architecture_project.data.datasource.remote

import com.example.architecture_project.data.datasource.remote.retrofit.NaverSevicelmpl
import com.example.architecture_project.data.model.NaverApi
import retrofit2.Call
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun getMovieData(
        title: String,
        onResponse: (NaverApi) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        NaverSevicelmpl.service.getMovie(title).enqueue(
            object : retrofit2.Callback<NaverApi> {
                override fun onFailure(call: Call<NaverApi>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(call: Call<NaverApi>, response: Response<NaverApi>) {
                    onResponse(response.body()!!)
                }

            }
        )
    }
}