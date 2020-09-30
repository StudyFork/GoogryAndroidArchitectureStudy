package com.hong.architecturestudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.ui.main.MainViewModel
import com.hong.architecturestudy.ui.main.adapter.MovieAdapter
import com.hong.architecturestudy.ui.moviedialog.adapter.MovieSearchListAdapter

@BindingAdapter("setItems")
fun RecyclerView.setAdapterItems(item: List<MovieData>?) {

    val movieAdapter = MovieAdapter()
    this.adapter = movieAdapter
    this.setHasFixedSize(true)

    item?.let { movieAdapter.setData(it) }
}

@BindingAdapter("setDialogItems", "bindVm", "setHasFixedSize")
fun RecyclerView.setDialogAdapterItems(item: List<MovieInfo>?, mainVm: MainViewModel? = null, setHasFixed: Boolean) {

    val movieSearchListAdapter = MovieSearchListAdapter()
    movieSearchListAdapter.vm = mainVm
    this.adapter = movieSearchListAdapter
    this.setHasFixedSize(setHasFixed)

    item?.let { movieSearchListAdapter.setList(it) }
}