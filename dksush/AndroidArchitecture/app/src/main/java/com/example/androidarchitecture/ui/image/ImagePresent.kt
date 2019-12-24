package com.example.androidarchitecture.ui.image

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.ui.base.BaseContract

class ImagePresent(
    private val view: BaseContract.View<ImageData>,
    private val repoInterface: NaverRepoInterface
) : BaseContract.Presenter {
    override fun requestList(text: String) {
        repoInterface.getImage(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())
            })

    }
}