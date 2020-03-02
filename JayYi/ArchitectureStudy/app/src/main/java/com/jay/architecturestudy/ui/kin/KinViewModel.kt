package com.jay.architecturestudy.ui.kin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.repository.model.Kin
import com.jay.repository.repository.NaverSearchRepository

class KinViewModel(
    override val repository: NaverSearchRepository
) : BaseViewModel<Kin>(repository) {
    override val _data: MutableLiveData<List<Kin>> = MutableLiveData()
    val data: LiveData<List<Kin>>
        get() = _data

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestKinResult()
            .compose(singleIoMainThread())
            .filter { it.keyword.isNotBlank() && it.kins.isNotEmpty() }
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.kins
            }, { e ->
                val message = e.message ?: return@subscribe
                _errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ kinRepo ->
                _data.value = kinRepo.kins
            }, { e ->
                val message = e.message ?: return@subscribe
                _errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}