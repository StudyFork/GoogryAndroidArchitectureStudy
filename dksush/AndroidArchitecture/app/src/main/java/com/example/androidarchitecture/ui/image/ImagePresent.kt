package com.example.androidarchitecture.ui.image

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.ui.base.ItemContract

class ImagePresent(
    private val view: ItemContract.View<ImageData>,
    private val naverRepositroy: NaverRepoInterface
) : ItemContract.Presenter {
    override suspend fun requestSearchHist() {
        naverRepositroy.getImageHist().let {
            if (it.isNotEmpty()) {
                view.isListEmpty(it.isEmpty())
                view.renderItems(it)
                view.inputKeyword(naverRepositroy.getImageKeyword())

            }
        }
    }

    override fun requestList(text: String) {
        naverRepositroy.saveImageKeyword(text)
        naverRepositroy.getImage(
            text, 1, 10,
            success = {
                view.isListEmpty(false)
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())
            })

    }
}