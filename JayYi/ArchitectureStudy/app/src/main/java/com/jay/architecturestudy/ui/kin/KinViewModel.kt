package com.jay.architecturestudy.ui.kin

import androidx.databinding.ObservableField
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class KinViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Kin>(repository) {
    override val data: ObservableField<List<Kin>> = ObservableField()

    override fun init() {
        repository.getLatestKinResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.kins)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .flatMap {
                repository.refreshKinSearchHistory(
                    keyword = keyword,
                    kins = it.kins
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ kinRepo ->
                viewType.set(
                    if (kinRepo.kins.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(kinRepo.kins)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}