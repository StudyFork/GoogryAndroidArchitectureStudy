package com.cnm.homework.ui

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.cnm.homework.applySchedulers
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.repository.NaverQueryRepositoryImpl
import com.cnm.homework.data.source.local.NaverQueryLocalDataSourceImpl
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val localDao: LocalDao) {

    private val naverQueryRepositoryImpl: NaverQueryRepositoryImpl by lazy {
        NaverQueryRepositoryImpl(
            NaverQueryRemoteDataSourceImpl(),
            NaverQueryLocalDataSourceImpl(localDao)
        )
    }
    private val disposable = CompositeDisposable()

    val movieItems = ObservableArrayList<NaverResponse.Item>()
    val searchString = ObservableField<String>()
    val toastString = ObservableField<String>()
    val isKeyboardBoolean = ObservableField<Boolean>()
    val isProgressBoolean = ObservableField<Boolean>()
    val isRvBoolean = ObservableField<Boolean>()
    val isFlBoolean = ObservableField<Boolean>()


    fun movieListSearch() {
        val query = searchString.get() as String
        if (query.isNotEmpty()) {
            disposable.add(naverQueryRepositoryImpl.getNaverMovie(query)
                .applySchedulers()
                .doOnSubscribe {
                    isProgressBoolean.set(true)
                    isKeyboardBoolean.set(false)
                }
                .doAfterTerminate {
                    isProgressBoolean.set(false)
                }
                .subscribe({
                    if (it.total != 0) {
                        movieItems.clear()
                        movieItems.addAll(it.items)
                        hideEmptyLayout()
                    }
                }, {
                    toastString.set("검색 결과가 없습니다.")
                    showEmptyLayout()
                })
            )
        } else {
            toastString.set("제목을 입력해주세요.")
        }
    }

    private fun showEmptyLayout() {
        isFlBoolean.set(true)
        isRvBoolean.set(false)
    }

    private fun hideEmptyLayout() {
        isFlBoolean.set(false)
        isRvBoolean.set(true)
    }

    fun disposableClear() = disposable.clear()

    fun showKeyboard() = isKeyboardBoolean.set(true)
}