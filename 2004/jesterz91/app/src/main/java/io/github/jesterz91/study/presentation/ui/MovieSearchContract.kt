package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseContract

interface MovieSearchContract {

    interface View : BaseContract.View {
        fun showSearchResult(items: List<Movie>)
        fun showLink(uri: Uri)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchMovie(query: CharSequence)
        fun browseMovie(uri: Uri)
        fun backPressed()
    }
}