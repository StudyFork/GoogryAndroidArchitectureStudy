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
        val lastKeyword = repository.getLatestKinKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestKinResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("kin", message)
                })
                .addTo(disposables)
        }
    }

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .flatMap {
                if (it.kins.isEmpty()) {
                    updateKinSearchHistory(
                        it.kins,
                        fun1 = { repository.clearKinResult() },
                        fun2 = { repository.saveKinKeyword(keyword) }
                    )
                } else {
                    val kinList = ensureKinEntityList(it.kins)
                    updateKinSearchHistory(
                        it.kins,
                        fun1 = { repository.clearKinResult() },
                        fun2 = { repository.saveKinKeyword(keyword) },
                        fun3 = { repository.saveKinResult(kinList) })
                }
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

    private fun ensureKinEntityList(kins: List<Kin>): List<KinEntity> =
        arrayListOf<KinEntity>().apply {
            kins.mapTo(this) { kin ->
                KinEntity(
                    description = kin.description,
                    link = kin.link,
                    title = kin.title
                )
            }
        }


    private fun updateKinSearchHistory(
        kins: List<Kin>,
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Single<List<Kin>> {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            firstCall
                .andThen(secondCall)
                .andThen(thirdCall)
                .toSingle { kins }
        } ?: run {
            firstCall
                .andThen(secondCall)
                .toSingle { kins }
        }
    }
}