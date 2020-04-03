package com.byiryu.study.ui.mvvm.main.viewmodel

import androidx.databinding.ObservableField
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.enums.NetStatus
import com.byiryu.study.ui.mvvm.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val repository: Repository): BaseViewModel(){

    val prevQuery = ObservableField<String>()
    val query = ObservableField<String>()
    val netStatus = ObservableField<NetStatus>()
    val status = ObservableField<Pair<Boolean, Any>>()
    val data = ObservableField<List<MovieItem>>()

    init {
        prevQuery.set(repository.getPrevSearchQuery())
    }

    fun search(){
        if (query.get().isNullOrEmpty()) {
            status.set(Pair(false, R.string.msg_search_value))
            status.notifyChange()
        } else {
            disposable.add(
                repository.getMovieList(query.get()!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        netStatus.set(NetStatus.LOADING)
                    }.doOnSuccess {
                        netStatus.set(NetStatus.SUCCESS)
                    }.doOnError {
                        netStatus.set(NetStatus.FAIL)
                        status.set(Pair(false, R.string.msg_error_loading))
                        status.notifyChange()
                    }
                    .subscribe({
                        data.set(it)
                    }, {
                        status.set(Pair(false, "오류발생 : $it"))
                        status.notifyChange()
                    })
            )

        }

    }

}
