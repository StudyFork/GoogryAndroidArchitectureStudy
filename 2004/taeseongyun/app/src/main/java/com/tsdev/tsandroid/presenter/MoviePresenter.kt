package com.tsdev.tsandroid.presenter

import com.tsdev.tsandroid.base.RecyclerViewModel
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePresenter(
    private val disposable: CompositeDisposable,
    private val movieRepositoryImpl: NaverReopsitory,
    private val movieRecyclerAdapter: RecyclerViewModel<Item>
) : MovieContract.Presenter {
    override fun loadMovie(query: String) {
        disposable.add(
            movieRepositoryImpl.getMovieList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieRecyclerAdapter.notifiedDataChange()
                    movieRecyclerAdapter.clear()
                    movieRecyclerAdapter.addItems(it)
                }, {
                    it.printStackTrace()
                })
        )
    }
}