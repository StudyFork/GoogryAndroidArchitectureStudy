package mi.song.class12android.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MovieViewModel(context: Context) {
    private val searchMovieRepository: SearchMovieRepository = SearchMovieRepositoryImpl(context)

    val message = ObservableField<String>()
    val successResult = ObservableField<List<MovieInfo>>()

    fun requestMovieData(query: String) {
        if (query.isEmpty()) return
        searchMovieRepository.getRemoteMovieData(query,
            success = {
                successResult.set(it)
            }, fail = {
                it.printStackTrace()
                message.set(it.message)
            })
    }
}