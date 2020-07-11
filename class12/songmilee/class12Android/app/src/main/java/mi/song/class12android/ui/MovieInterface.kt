package mi.song.class12android.ui

interface MovieInterface {
    interface View {

    }

    interface Presenter {
        fun requestMovieData(query:String)
    }
}