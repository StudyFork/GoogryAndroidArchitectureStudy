package mi.song.class12android.viewmodel

import androidx.databinding.ObservableField
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository

class MovieViewModel(private val searchMovieRepository: SearchMovieRepository) {
    val message = ObservableField<String>()
    val successResult = ObservableField<List<MovieInfo>>()
    val query = ObservableField<String>()

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