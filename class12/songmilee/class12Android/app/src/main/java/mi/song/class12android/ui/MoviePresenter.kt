package mi.song.class12android.ui

import android.content.Context
import mi.song.class12android.R
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MoviePresenter(val context: Context, val view:MovieInterface.View) : MovieInterface.Presenter {
    private val searchMovieRepository: SearchMovieRepository by lazy {
        SearchMovieRepositoryImpl(context)
    }

    override fun requestMovieData(query: String) {
        if (query.isEmpty()) return

        searchMovieRepository.getRemoteMovieData(query,
            success = {

        }, fail = {
            view.showMessage(it.message?:context.getString(R.string.unknown_error))
        })
    }
}