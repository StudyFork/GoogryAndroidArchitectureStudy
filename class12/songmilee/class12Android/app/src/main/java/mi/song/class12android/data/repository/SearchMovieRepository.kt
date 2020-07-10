package mi.song.class12android.data.repository

import mi.song.class12android.data.model.MovieInfo

interface SearchMovieRepository {
    fun getRemoteMovieData(query: String, success: (List<MovieInfo>) -> Unit, fail: (Throwable) -> Unit)
}