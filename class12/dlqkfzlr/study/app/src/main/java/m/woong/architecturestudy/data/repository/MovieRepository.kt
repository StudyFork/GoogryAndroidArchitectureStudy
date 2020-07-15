package m.woong.architecturestudy.data.repository

import m.woong.architecturestudy.data.source.remote.model.MovieResponse

interface MovieRepository {

    fun getRecentMovie(query: String,
                       success: (MovieResponse) -> Unit,
                       failure: (Throwable) -> Unit)

    fun getMovie(query: String,
    success: (MovieResponse) -> Unit,
    failure: (Throwable) -> Unit)

    fun getCachedMovie()

    fun isRecentMovie():Boolean
}