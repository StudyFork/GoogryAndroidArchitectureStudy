package mi.song.class12android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mi.song.class12android.data.repository.SearchMovieRepository

class MovieViewModelFactory constructor(val searchMovieRepository: SearchMovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(searchMovieRepository) as T
    }
}