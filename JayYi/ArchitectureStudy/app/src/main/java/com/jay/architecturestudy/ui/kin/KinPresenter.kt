package com.jay.architecturestudy.ui.kin

import android.util.Log
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single

class KinPresenter(
    override val view: KinContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), KinContract.Presenter {

    override fun subscribe() {
        repository.getLatestKinResult()
            .compose(singleIoMainThread())
            .subscribe({
                view.updateUi(it.keyword, it.kins)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("kin", message)
            })
            .addTo(disposables)
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
                val kins = kinRepo.kins
                if (kins.isEmpty()) {
                } else {
                }
                view.updateResult(kins)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}