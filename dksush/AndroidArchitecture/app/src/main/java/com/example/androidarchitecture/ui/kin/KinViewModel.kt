package com.example.androidarchitecture.ui.kin

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData

class KinViewModel(private val naverRepositroy: NaverRepoInterface) {


    private val _renderItems = MutableLiveData<List<KinData>>()
    val renderItems get() = _renderItems
    val isListEmpty = MutableLiveData<Boolean>(true)
    val blankInputText = MutableLiveData<Unit>()
    val errorToast = MutableLiveData<Throwable>()
    var inputKeyword = naverRepositroy.getKinKeyword()

    suspend fun requestSearchHist() {
        naverRepositroy.getKinHist().let {
            if (it.isNotEmpty()) {
                isListEmpty.value = it.isEmpty()
                _renderItems.value = it

            }
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.value = Unit
        } else {
            naverRepositroy.saveKinKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getKin(inputKeyword, 1, 10,
                success = {
                    isListEmpty.value = false
                    _renderItems.value = it
                }, fail = {
                    errorToast.value = it
                })
        }
    }
}