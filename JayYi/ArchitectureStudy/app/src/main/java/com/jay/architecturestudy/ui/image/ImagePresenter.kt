package com.jay.architecturestudy.ui.image

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .subscribe({ responseImage ->
                view.updateResult(responseImage.images)
            }, { e ->
                handleError(e)
            })
    }
}