package com.example.androidarchitecture.ui.kin

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.ui.base.ItemContract

class KinPresent(
    private val view: ItemContract.View<KinData>,
    private val repoInterface: NaverRepoInterface
) : ItemContract.Presenter {
    override fun requestList(text: String) {
        repoInterface.getKin(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())
            })

    }
}