package com.example.architecturestudy.ui.movie

import android.util.Log
import com.example.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviePresenter(
    val view: MovieContract.View,
    private val repository: NaverSearchRepository?
) : MovieContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun taskSearch(isNetwork: Boolean, keyword: String) {
        if (repository != null) {

            // 생성자
            val searchSingle = repository.getMovie(
                keyword = keyword
            )
                // 스레드 변경
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    // 성공처리
                    {
                        view.showResult(it.items)
                    },
                    // 실패 처리
                    {
                        it.printStackTrace()
                        view.showErrorMessage(it.toString())
                    }
                )
            disposable.add(searchSingle)
        }
    }

    override fun getLastData() {
        if (repository == null) return

        val data = repository.getLastMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.showResult(it)
                },
                {
                    view.showErrorMessage(it.toString())
                }
            )
        disposable.add(data)
    }

    override fun onStop() {
        disposable.clear()
    }
}