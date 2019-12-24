package com.example.androidarchitecture.ui.kin

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.ui.base.NaverContract

class KinPresent(
    private val view: NaverContract.View<KinData>,
    private val repoInterface: NaverRepoInterface
) : NaverContract.Presenter {
    override fun requestList(text: String) {
        repoInterface.getKin(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())
            })

    }
}