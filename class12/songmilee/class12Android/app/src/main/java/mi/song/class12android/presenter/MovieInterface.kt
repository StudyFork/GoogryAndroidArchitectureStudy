package mi.song.class12android.presenter

import mi.song.class12android.data.model.MovieInfo

interface MovieInterface {
    interface View {
        fun showMessage(msg: String)
        fun updateMovieList(list: List<MovieInfo>)
        fun queryMovie()
    }

    interface Presenter {
        fun requestMovieData(query: String)
    }
}