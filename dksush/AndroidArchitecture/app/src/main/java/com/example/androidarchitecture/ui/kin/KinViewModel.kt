package com.example.androidarchitecture.ui.kin

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData

class KinViewModel(private val naverRepositroy: NaverRepoInterface) : ViewModel(){


    private val _renderItems = MutableLiveData<List<KinData>>()
    val renderItems:LiveData<List<KinData>> get() = _renderItems
    val isListEmpty : LiveData<Boolean> = Transformations.map(_renderItems){
        it.isEmpty()
    }


    val blankInputText = MutableLiveData<Unit>()
    val errorToast = MutableLiveData<Throwable>()
    var inputKeyword = naverRepositroy.getKinKeyword()

    suspend fun requestSearchHist() {
        naverRepositroy.getKinHist().let {
            _renderItems.value = it
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.value = Unit
        } else {
            naverRepositroy.saveKinKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getKin(inputKeyword, 1, 10,
                success = {
                    _renderItems.value = it
                }, fail = {
                    errorToast.value = it
                })
        }
    }
}