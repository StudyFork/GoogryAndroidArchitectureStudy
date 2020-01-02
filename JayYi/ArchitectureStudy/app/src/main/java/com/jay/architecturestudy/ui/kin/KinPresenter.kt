package com.jay.architecturestudy.ui.kin

import android.util.Log
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KinPresenter(
    override val view: KinContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), KinContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestKinKeyword()
        loadKinSearchHistory(
            keyword = lastKeyword
        )
            .subscribe({
                view.updateUi(it.first, it.second)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("kin", message)
            })
            .addTo(disposables)
    }

    private fun loadKinSearchHistory(keyword: String) : Single<Pair<String, List<Kin>>> {
        return if (keyword.isBlank()) {
            Single.just(Pair(keyword, emptyList()))
        } else {
            repository.getLatestKinResult()
                .map { Pair(keyword, it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                clearSearchHistory { repository.clearKinResult() }
                it.kins.isNotEmpty().then {
                    val kinList = arrayListOf<KinEntity>()
                    it.kins.mapTo(kinList) { kin ->
                        KinEntity(
                            description = kin.description,
                            link = kin.link,
                            title = kin.title
                        )
                    }
                    // 최신 결과 저장
                    updateSearchHistory { repository.saveKinResult(kinList) }
                }
                repository.saveKinKeyword(keyword)
                it.kins
            }
            .compose(singleIoMainThread())
            .subscribe({ kins ->
                if (kins.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(kins)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}