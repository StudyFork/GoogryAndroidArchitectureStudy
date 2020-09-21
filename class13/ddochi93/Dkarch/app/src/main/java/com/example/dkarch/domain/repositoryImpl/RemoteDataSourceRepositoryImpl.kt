package com.example.dkarch.domain.repositoryImpl

import com.example.dkarch.data.response.MovieResponse
import com.example.dkarch.domain.api.usecase.GetMovieListUseCase
import com.example.dkarch.domain.repository.RemoteDataSourceRepository
import io.reactivex.Single
import retrofit2.Response

class RemoteDataSourceRepositoryImpl(private val getMovieListUseCase: GetMovieListUseCase) :
    RemoteDataSourceRepository {
    override fun getMovies(query: String): Single<Response<MovieResponse>> {
        return getMovieListUseCase.getMovieList(query)
    }

}
