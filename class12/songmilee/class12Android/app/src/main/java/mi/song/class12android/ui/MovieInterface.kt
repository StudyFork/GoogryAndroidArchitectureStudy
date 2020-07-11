package mi.song.class12android.ui

interface MovieInterface {
    interface View {
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun requestMovieData(query: String)
    }
}