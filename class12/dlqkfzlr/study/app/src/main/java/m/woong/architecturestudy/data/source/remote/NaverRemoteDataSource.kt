package m.woong.architecturestudy.data.source.remote

import m.woong.architecturestudy.data.source.remote.model.MovieResponse

interface NaverRemoteDataSource {

    fun getMovie(
        query: String,
        success: (MovieResponse) -> Unit,
        failure: (Throwable) -> Unit
    )
}