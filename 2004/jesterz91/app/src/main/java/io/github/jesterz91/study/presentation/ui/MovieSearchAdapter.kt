package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.presentation.common.BaseAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MovieSearchAdapter : BaseAdapter<Movie, MovieSearchViewHolder>() {

    private val uriSubject = PublishSubject.create<Uri>()

    override fun createViewHolder(layoutInflater: LayoutInflater): MovieSearchViewHolder {
        return MovieSearchViewHolder(
            layoutInflater
        ).apply {
            binding.root.clicks()
                .map { adapterPosition }
                .filter { it != RecyclerView.NO_POSITION }
                .map(::getItem)
                .map(Movie::link)
                .map(Uri::parse)
                .subscribe(uriSubject)
        }
    }

    fun getClickObservable(): Observable<Uri> = uriSubject
}