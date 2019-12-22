package com.jay.architecturestudy.ui.image

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl

class ImagePresenter(
    private val view: ImageContract.View,
    private val repository: NaverSearchRepositoryImpl
): ImageContract.Presenter {

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword,
            success = {responseImage ->
                view.updateResult(responseImage.images)
            },
            fail = { e ->
                val message = e.message ?: return@getImage
                view.showErrorMessage(message)
            }
        )
    }
}