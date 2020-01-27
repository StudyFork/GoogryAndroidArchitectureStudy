package com.jay.architecturestudy.ui.image

import androidx.databinding.ObservableField
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class ImageViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Image>(repository) {
    override val data: ObservableField<List<Image>> = ObservableField()

    override fun init() {
        repository.getLatestImageResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.images)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ imageRepo ->
                viewType.set(
                    if (imageRepo.images.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(imageRepo.images)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}