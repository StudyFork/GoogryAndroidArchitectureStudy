package mi.song.class12android.ui

import android.content.Context
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MoviePresenter(val context: Context) : MovieInterface.Presenter {
    private val searchMovieRepository: SearchMovieRepository by lazy {
        SearchMovieRepositoryImpl(context)
    }

    override fun requestMovieData(query: String) {
        if (query.isEmpty()) return

        searchMovieRepository.getRemoteMovieData(query,
            success = {

        }, fail = {

        })
    }
}