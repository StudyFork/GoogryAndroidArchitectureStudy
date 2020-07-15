package m.woong.architecturestudy

interface MoviePresenter : MovieContract.Presenter {

    override fun requestMovieList(query: String)

    fun getRecentMovie(query: String)

    fun showErrorEmptyQuery()
}