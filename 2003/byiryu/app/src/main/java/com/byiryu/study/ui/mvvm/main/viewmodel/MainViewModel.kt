package com.byiryu.study.ui.mvvm.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.enums.NetStatus
import com.byiryu.study.ui.mvvm.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val repository: Repository): BaseViewModel(){

    val prevQuery = MutableLiveData<String>()
    val query = MutableLiveData<String>()
    val netStatus = MutableLiveData<NetStatus>()
    val status = MutableLiveData<Pair<Boolean, Any>>()
    val movieData = MutableLiveData<List<MovieItem>>()

    init {
        prevQuery.value = repository.getPrevSearchQuery()
    }

    fun search(){
        if (query.value.isNullOrEmpty()) {
            status.value = Pair(false, R.string.msg_search_value)
        } else {
            disposable.add(
                repository.getMovieList(query.value!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        netStatus.value = NetStatus.LOADING
                    }.doOnSuccess {
                        netStatus.value = NetStatus.SUCCESS
                    }.doOnError {
                        netStatus.value = NetStatus.FAIL
                        status.value = Pair(false, R.string.msg_error_loading)
                    }
                    .subscribe({
                        movieData.value = it
                    }, {
                        status.value = Pair(false, "오류발생 : $it")
                    })
            )
        }

    }

}
