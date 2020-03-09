package com.example.architecturestudy.ui.image

import com.example.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImagePresenter(
    val view : ImageContract.View,
    private val repository: NaverSearchRepository?
    ) : ImageContract.Presenter{

    private val disposable = CompositeDisposable()

    override fun taskSearch(keyword: String) {
        repository?.let {
            val searchSingle = repository?.getImage(
                keyword = keyword
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.showResult(it.items)
                    },
                    {
                        view.showErrorMessage(it.toString())
                    }
                )
            disposable.add(searchSingle)
        }
    }

    override fun getLastData() {
        repository?.let {
            val data = repository.getLastImage()
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
    }

    override fun onStop() {
        disposable.clear()
    }
}
