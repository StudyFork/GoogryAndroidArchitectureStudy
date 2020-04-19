package io.github.sooakim.remote

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.data.remote.SAMovieRemoteDataSource
import io.github.sooakim.remote.api.SANaverMovieApi
import io.github.sooakim.remote.mapper.SAMovieRemoteMapper
import io.reactivex.Single

internal class SAMovieRemoteDataSourceImpl(
    private val movieApi: SANaverMovieApi
) : SAMovieRemoteDataSource {
    override fun getMovies(query: String): Single<List<SAMovieData>> {
        return movieApi.getSearchMovie(query)
            .map { it.items.map(SAMovieRemoteMapper::mapToData) }
    }
}