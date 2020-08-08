package mi.song.class12android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository

class MovieViewModel(private val searchMovieRepository: SearchMovieRepository) : ViewModel() {
    private val _message = MutableLiveData<String>()
    private val _successResult = MutableLiveData<List<MovieInfo>>()

    val message: LiveData<String> = _message
    val successResult: LiveData<List<MovieInfo>> = _successResult

    val query = MutableLiveData<String>()

    fun requestMovieData(query: String) {
        if (query.isEmpty()) return
        searchMovieRepository.getRemoteMovieData(query,
            success = {
                _successResult.value = it
            }, fail = {
                it.printStackTrace()
                _message.value = it.message
            })
    }
}