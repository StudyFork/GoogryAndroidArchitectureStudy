package com.jay.architecturestudy.ui

import android.view.View
import androidx.databinding.ObservableField
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.util.hideKeyboard
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T>(
    protected open val repository: NaverSearchRepository
) {
    val viewType = ObservableField<ViewType>(ViewType.VIEW_SEARCH_BEFORE)
    val errorMsg = ObservableField<String?>()
    val keyword = ObservableField<String?>()
    val invalidKeyword = ObservableField<Boolean>()
    protected val compositeDisposable = CompositeDisposable()

    private val debounceTime: Long = 600L
    private var lastClickTime: Long = 0

    abstract val data: ObservableField<List<T>>

    abstract fun search(keyword: String)

    abstract fun init()

    fun onClick(v: View, keyword: String) {
        if (System.currentTimeMillis() - lastClickTime < debounceTime) {
            return
        }
        if (keyword.isBlank()) {
            invalidKeyword.set(true)
        } else {
            invalidKeyword.set(false)
            v.hideKeyboard()
            search(keyword)
        }
    }

    fun onCleared() {
        compositeDisposable.clear()
    }
}

enum class ViewType(type: Int) {
    VIEW_SEARCH_BEFORE(0),
    VIEW_SEARCH_SUCCESS(1),
    VIEW_SEARCH_NO_RESULT(2),
    ;
}