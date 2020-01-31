package com.jay.architecturestudy.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class ImageViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Image>(repository) {
    override val _data: MutableLiveData<List<Image>> = MutableLiveData()
    val data: LiveData<List<Image>>
        get() = _data

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestImageResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.images
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ imageRepo ->
                _data.value = imageRepo.images
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}