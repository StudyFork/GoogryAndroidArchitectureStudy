package com.jay.architecturestudy.ui.image

import android.util.Log
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun subscribe() {
        repository.getLatestImageResult()
            .compose(singleIoMainThread())
            .subscribe({
                view.updateUi(it.keyword, it.images)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("image", message)
            })
            .addTo(disposables)
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .flatMap {
                repository.refreshImageSearchHistory(
                    keyword = keyword,
                    images = it.images
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ imageRepo ->
                val images = imageRepo.images
                if (images.isEmpty()) {
                } else {
                }
                view.updateResult(images)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}