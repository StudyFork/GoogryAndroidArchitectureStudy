package io.github.sooakim.data.remote.source

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.data.remote.api.SANaverMovieApi
import io.github.sooakim.data.remote.mapper.SAMovieRemoteMapper
import io.reactivex.Single

class SAMovieRemoteDataSourceImpl(
    private val movieApi: SANaverMovieApi
) : SAMovieRemoteDataSource {
    override fun getMovies(query: String): Single<List<SAMovieData>> {
        return movieApi.getSearchMovie(query)
            .map { it.items.map(SAMovieRemoteMapper::mapToData) }
    }
}