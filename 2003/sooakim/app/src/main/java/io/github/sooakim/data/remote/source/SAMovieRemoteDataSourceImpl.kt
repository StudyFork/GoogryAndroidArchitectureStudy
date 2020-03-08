package io.github.sooakim.data.remote.source

import io.github.sooakim.data.model.SAMovieEntity
import io.github.sooakim.data.remote.api.SANaverMovieApi
import io.github.sooakim.data.remote.mapper.SAMovieRemoteMapper
import io.github.sooakim.data.remote.utils.throwIfEmpty
import io.reactivex.Single

class SAMovieRemoteDataSourceImpl(
    private val movieApi: SANaverMovieApi,
    private val movieRemoteMapper: SAMovieRemoteMapper
) : SAMovieRemoteDataSource {
    override fun getMovies(query: String): Single<List<SAMovieEntity>> {
        return movieApi.getSearchMovie(query)
            .map { it.items.map(movieRemoteMapper::mapToEntity) }
            .throwIfEmpty()
    }
}