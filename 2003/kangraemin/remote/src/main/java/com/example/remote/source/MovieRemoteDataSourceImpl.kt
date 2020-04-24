package com.example.remote.source

import com.example.data.model.Movie
import com.example.data.source.remote.MovieRemoteDataSource
import com.example.remote.mapper.RemoteMovieMapper
import io.reactivex.Single

internal class MovieRemoteDataSourceImpl(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovies(query: String): Single<List<Movie>> {
        return movieApi.getSearchItems(query = query)
            .map {
                RemoteMovieMapper.remoteMovieToDataMovie(it)
            }
    }
}