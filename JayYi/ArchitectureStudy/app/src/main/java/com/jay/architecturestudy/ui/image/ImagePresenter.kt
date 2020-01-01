package com.jay.architecturestudy.ui.image

import android.util.Log
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        loadData()
    }

    private fun loadData() {
        val lastKeyword = repository.getLatestImageKeyword()
        if (lastKeyword.isBlank()) {
            view.updateUi(lastKeyword, emptyList())
        } else {
            repository.getLatestImageResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("blog", message)
                })
                .addTo(disposables)
        }
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                updateBlogResult { repository.clearImageResult() }
                it.images.isNotEmpty().then {
                    val imageList = arrayListOf<ImageEntity>()
                    it.images.mapTo(imageList) { image ->
                        ImageEntity(
                           link = image.link,
                            sizeWidth = image.sizeWidth,
                            sizeHeight = image.sizeHeight,
                            thumbnail = image.thumbnail,
                            title = image.title
                        )
                    }
                    updateBlogResult {
                        // 최신 결과 저장
                        repository.saveImageResult(imageList)
                    }
                }
                repository.saveImageKeyword(keyword)
                it.images
            }
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

    private fun updateBlogResult(func: () -> Unit) {
        Completable.fromCallable(func)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}