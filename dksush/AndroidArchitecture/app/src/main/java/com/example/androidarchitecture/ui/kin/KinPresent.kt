package com.example.androidarchitecture.ui.kin

import android.text.TextUtils
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.ui.base.ItemContract

class KinPresent(
    private val view: ItemContract.View<KinData>,
    private val naverRepositroy: NaverRepoInterface
) : ItemContract.Presenter {
    override suspend fun requestSearchHist() {
        naverRepositroy.getKinHist().let {
            if (it.isNotEmpty()) {
                view.isListEmpty(it.isEmpty())
                view.renderItems(it)
                view.inputKeyword(naverRepositroy.getKinKeyword())
            }
        }
    }

    override fun requestList(text: String) {
        if (TextUtils.isEmpty(text)) {
            view.blankInputText()

        } else {
            naverRepositroy.saveKinKeyword(text)
            naverRepositroy.getKin(text, 1, 10,
                success = {
                    view.isListEmpty(false)
                    view.renderItems(it)
                }, fail = {
                    view.errorToast(it.toString())
                })
        }


    }
}