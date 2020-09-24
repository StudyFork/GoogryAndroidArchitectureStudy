package com.example.dkarch.domain.repositoryImpl

import com.example.dkarch.data.response.MovieResponse
import com.example.dkarch.domain.api.usecase.GetMovieListUseCase
import com.example.dkarch.domain.repository.LocalDataSourceRepository
import com.example.dkarch.domain.repository.NaverMovieRepository
import com.example.dkarch.domain.repository.RemoteDataSourceRepository
import io.reactivex.Single
import retrofit2.Response

class NaverMovieRepositoryImpl(private val getMovieListUseCase: GetMovieListUseCase) :
    NaverMovieRepository {
    private val remoteDataSourceRepository: RemoteDataSourceRepository =
        RemoteDataSourceRepositoryImpl(getMovieListUseCase)
    private val localDataSourceRepository: LocalDataSourceRepository =
        LocalDataSourceRepositoryImpl()

    override fun getMovies(query: String): Single<Response<MovieResponse>> {
        return remoteDataSourceRepository.getMovies(query)
            .doOnSuccess { localDataSourceRepository.saveQuery(query) }
    }

    override fun getQueryList(): List<String> {
        return localDataSourceRepository.getSavedQueryList()
    }

}
