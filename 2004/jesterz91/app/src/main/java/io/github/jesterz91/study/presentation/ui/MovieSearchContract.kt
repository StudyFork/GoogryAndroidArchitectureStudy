package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseContract
import io.reactivex.Observable

interface MovieSearchContract {

    interface View : BaseContract.View {
        fun fetchSearchResult(items: List<Movie>)
        fun getClickObservable() : Observable<Uri>
        fun showLink(uri: Uri)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchMovie(query: CharSequence)
        fun backPressed()
    }
}