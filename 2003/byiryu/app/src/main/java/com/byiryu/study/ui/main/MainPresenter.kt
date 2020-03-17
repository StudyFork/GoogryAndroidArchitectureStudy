package com.byiryu.study.ui.main

import android.content.Context
import android.util.Log
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.BRApplication
import com.byiryu.study.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter<V : MainConract.View> constructor(private val repository: Repository): BasePresenter<V>(), MainConract.Presenter<V> {



    override fun onViewPrepared() {
        mvpView?.setPrevQuery(repository.getPrevSearchQuery())

    }

    override fun search(query: String) {
        if (query.isEmpty()) {
            mvpView?.showMsg(R.string.msg_search_value)
        } else {

            disposable =
                repository.getMovieList(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        mvpView?.showLoading()
                    }.doOnSuccess {
                        mvpView?.hideLoading()
                    }.doOnError {
                        mvpView?.hideLoading()
                        mvpView?.showMsg(R.string.msg_error_loading)
                    }
                    .subscribe({
                        mvpView?.setResult(it)
                    }, {
                        mvpView?.showMsg("오류 발생 $it")
                    })

        }

    }

}