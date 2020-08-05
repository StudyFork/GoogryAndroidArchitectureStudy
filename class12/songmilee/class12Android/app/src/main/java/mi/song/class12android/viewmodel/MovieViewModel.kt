package mi.song.class12android.viewmodel

import androidx.lifecycle.MutableLiveData
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository

class MovieViewModel(private val searchMovieRepository: SearchMovieRepository) {
    val message = MutableLiveData<String>()
    val successResult = MutableLiveData<List<MovieInfo>>()
    val query = MutableLiveData<String>()

    fun requestMovieData(query: String) {
        if (query.isEmpty()) return
        searchMovieRepository.getRemoteMovieData(query,
            success = {
                successResult.value = it
                android.util.Log.d("set value", "${it[0]}")
            }, fail = {
                it.printStackTrace()
                message.value = it.message
            })
    }
}