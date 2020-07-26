package mi.song.class12android.viewmodel

import android.content.Context
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MovieViewModel(private val context: Context) {
    private val searchMovieRepository: SearchMovieRepository = SearchMovieRepositoryImpl(context)

    fun requestMovieData(query: String) {
        if (query.isEmpty()) return
        searchMovieRepository.getRemoteMovieData(query,
            success = {

            }, fail = {

            })
    }
}