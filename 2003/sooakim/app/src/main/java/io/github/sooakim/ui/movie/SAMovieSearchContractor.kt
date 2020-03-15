package io.github.sooakim.ui.movie

import android.net.Uri
import io.github.sooakim.ui.base.SABasePresenter
import io.github.sooakim.ui.base.SABaseView
import io.github.sooakim.ui.movie.model.SAMoviePresentation

interface SAMovieSearchContractor {
    interface View : SABaseView {
        fun setSearchText(text: String)

        fun showSearchResults(results: List<SAMoviePresentation>)
        fun showLink(uri: Uri)
    }

    interface Presenter : SABasePresenter {
        fun doSearch(text: String)

        fun onSearchChanges(text: String)
        fun onSearchResultClick(item: SAMoviePresentation)
    }
}