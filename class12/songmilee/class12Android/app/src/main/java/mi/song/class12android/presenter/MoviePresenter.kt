package mi.song.class12android.presenter

import android.content.Context
import mi.song.class12android.R
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MoviePresenter(private val context: Context, private val view: MovieInterface.View) :
    MovieInterface.Presenter {
    private val searchMovieRepository: SearchMovieRepository = SearchMovieRepositoryImpl(context)

    override fun requestMovieData(query: String) {
        if (query.isEmpty()) return

        searchMovieRepository.getRemoteMovieData(query,
            success = {
                view.updateMovieList(it)
            }, fail = {
                view.showMessage(it.message ?: context.getString(R.string.unknown_error))
            })
    }
}