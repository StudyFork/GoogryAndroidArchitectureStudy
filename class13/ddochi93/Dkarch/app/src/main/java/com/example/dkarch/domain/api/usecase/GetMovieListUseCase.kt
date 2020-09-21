package com.example.dkarch.domain.api.usecase

import com.example.dkarch.data.response.MovieResponse
import com.example.dkarch.domain.api.service.NaverMovieService
import com.example.dkarch.domain.repository.RetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class GetMovieListUseCase(retrofitRepository: RetrofitRepository) {
    private val naverMovieService =
        retrofitRepository.getAccessRetrofit().create(NaverMovieService::class.java)

    fun getMovieList(query: String): Single<Response<MovieResponse>> =
        naverMovieService.getMovieList(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
