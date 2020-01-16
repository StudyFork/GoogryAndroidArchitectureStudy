package com.example.androidarchitecture.ui.kin

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData

class KinViewModel(private val naverRepositroy: NaverRepoInterface) {

    var inputKeyword = naverRepositroy.getKinKeyword()
    val blankInputText = ObservableField<Unit>()
    val renderItems = ObservableField<List<KinData>>()
    val errorToast = ObservableField<Throwable>()
    val isListEmpty = ObservableField<Boolean>(true)

    suspend fun requestSearchHist() {
        naverRepositroy.getKinHist().let {
            if (it.isNotEmpty()) {
                isListEmpty.set(it.isEmpty())
                renderItems.set(it)

            }
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.set(Unit)
        } else {
            naverRepositroy.saveKinKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getKin(inputKeyword, 1, 10,
                success = {
                    isListEmpty.set(false)
                    renderItems.set(it)
                }, fail = {
                    errorToast.set(Throwable())
                })
        }
    }
}