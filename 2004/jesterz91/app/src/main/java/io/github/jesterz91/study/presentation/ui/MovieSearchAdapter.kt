package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import android.view.LayoutInflater
import com.jakewharton.rxbinding3.view.clicks
import io.github.jesterz91.study.data.model.Movie
import io.github.jesterz91.study.presentation.common.BaseAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MovieSearchAdapter(override val items: MutableList<Movie>) : BaseAdapter<Movie, MovieSearchViewHolder>() {

    private val uriSubject = PublishSubject.create<Uri>()

    override fun createViewHolder(layoutInflater: LayoutInflater): MovieSearchViewHolder {
        return MovieSearchViewHolder(
            layoutInflater
        ).apply {
            binding.root.clicks()
                .map { getItem(adapterPosition) }
                .map { it.link }
                .map(Uri::parse)
                .subscribe(uriSubject)
        }
    }

    fun getClickObservable(): Observable<Uri> = uriSubject

    fun changeItems(newItems: List<Movie>) {
        items.clear()
        items.addAll(newItems)
    }
}