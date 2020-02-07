package com.example.androidarchitecture.ui.image

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.ImageData

class ImageViewModel(private val naverRepositroy: NaverRepoInterface) {

    private val _renderItems = MutableLiveData<List<ImageData>>()
    val renderItems: LiveData<List<ImageData>> get() = _renderItems
    val isListEmpty: LiveData<Boolean> = Transformations.map(_renderItems) {
        it.isEmpty()
    }
    val blankInputText = MutableLiveData<Unit>()
    val errorToast = MutableLiveData<Throwable>()
    var inputKeyword = naverRepositroy.getImageKeyword()


    suspend fun requestSearchHist() {
        naverRepositroy.getImageHist().let {
            _renderItems.value = it

        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.value = Unit
        } else {
            naverRepositroy.saveImageKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getImage(inputKeyword, 1, 10,
                success = {
                    _renderItems.value = it
                }, fail = {
                    errorToast.value = it
                })
        }
    }
}