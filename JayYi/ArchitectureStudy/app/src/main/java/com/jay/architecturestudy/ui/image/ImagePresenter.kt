package com.jay.architecturestudy.ui.image

import android.util.Log
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestImageKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestImageResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("image", message)
                })
                .addTo(disposables)
        }
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
            .subscribe({ images ->
                if (images.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(images)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}