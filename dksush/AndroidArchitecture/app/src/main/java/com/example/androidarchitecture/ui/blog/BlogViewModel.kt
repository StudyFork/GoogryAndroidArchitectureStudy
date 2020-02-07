package com.example.androidarchitecture.ui.blog

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.BlogData

class BlogViewModel(private val naverRepositroy: NaverRepoInterface) : ViewModel() {

    private val _renderItems = MutableLiveData<List<BlogData>>()
    val renderItems: LiveData<List<BlogData>> get() = _renderItems
    val isListEmpty: LiveData<Boolean> = Transformations.map(_renderItems) {
        it.isEmpty()
    }

    val blankInputText = MutableLiveData<Unit>()
    val errorToast = MutableLiveData<Throwable>()
    var inputKeyword = naverRepositroy.getBlogKeyword()


    suspend fun requestSearchHist() {
        naverRepositroy.getBlogHist().let {
            _renderItems.value = it
        }
    }

    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.value = Unit
        } else {
            naverRepositroy.saveBlogKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getBlog(inputKeyword, 1, 10,
                success = {
                    _renderItems.value = it
                }, fail = {
                    errorToast.value = it
                })
        }
    }
}