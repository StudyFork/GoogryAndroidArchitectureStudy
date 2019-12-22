package com.jay.architecturestudy.ui.image

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BasePresenter

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BasePresenter(view, repository), ImageContract.Presenter {

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword,
            success = { responseImage ->
                view.updateResult(responseImage.images)
            },
            fail = { e ->
                handlerError(e)
            }
        )
    }
}