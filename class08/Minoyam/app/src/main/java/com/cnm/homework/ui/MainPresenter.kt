package com.cnm.homework.ui

import android.content.Context
import com.cnm.homework.applySchedulers
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.repository.NaverQueryRepositoryImpl
import com.cnm.homework.data.source.local.NaverQueryLocalDataSourceImpl
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalDatabase
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val view: MainContract.View, private val context: Context) :
    MainContract.Presenter {

    private val localDao: LocalDao by lazy {
        val db = LocalDatabase.getInstance(context)!!
        db.localDao()
    }
    private val naverQueryRepositoryImpl: NaverQueryRepositoryImpl by lazy {
        NaverQueryRepositoryImpl(
            NaverQueryRemoteDataSourceImpl(),
            NaverQueryLocalDataSourceImpl(localDao)
        )
    }
    private val disposable = CompositeDisposable()

    override fun movieListSearch(query: String) {
        if (query.isNotEmpty()) {
            disposable.add(naverQueryRepositoryImpl.getNaverMovie(query)
                .applySchedulers()
                .doOnSubscribe {
                    view.showProgress()
                }
                .doAfterTerminate {
                    view.hideProgress()
                }
                .subscribe({
                    if (it.total != 0) {
                        view.setItem(it.items)
                        view.hideEmptyLayout()
                    } else {
                        view.showErrorEmptyResult()
                        view.showEmptyLayout()
                    }
                }, {
                    it.printStackTrace()
                })
            )
        } else {
            view.showErrorEmptyQuery()
        }
    }

    override fun disposableClear() = disposable.clear()

    override fun loadMovieList(): List<NaverResponse.Item> = naverQueryRepositoryImpl.loadLocal()


}