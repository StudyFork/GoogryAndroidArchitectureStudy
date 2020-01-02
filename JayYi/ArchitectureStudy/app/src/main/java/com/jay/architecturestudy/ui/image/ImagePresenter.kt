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
                if (it.images.isEmpty()) {
                    updateImageSearchHistory(
                        it.images,
                        fun1 = { repository.clearImageResult() },
                        fun2 = { repository.saveImageKeyword(keyword) }
                    )
                } else {
                    val imageList = ensureImageEntityList(it.images)
                    updateImageSearchHistory(
                        it.images,
                        fun1 = { repository.clearImageResult() },
                        fun2 = { repository.saveImageKeyword(keyword) },
                        fun3 = { repository.saveImageResult(imageList) })
                }
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

    private fun ensureImageEntityList(images: List<Image>): List<ImageEntity> =
        arrayListOf<ImageEntity>().apply {
            images.mapTo(this) { image ->
                ImageEntity(
                    link = image.link,
                    sizeWidth = image.sizeWidth,
                    sizeHeight = image.sizeHeight,
                    thumbnail = image.thumbnail,
                    title = image.title
                )
            }
        }

    private fun updateImageSearchHistory(
        images: List<Image>,
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Single<List<Image>> {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            firstCall
                .andThen(secondCall)
                .andThen(thirdCall)
                .toSingle { images }
        } ?: run {
            firstCall
                .andThen(secondCall)
                .toSingle { images }
        }
    }
}