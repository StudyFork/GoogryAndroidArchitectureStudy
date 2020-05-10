package com.tsdev.tsandroid.ui.viewmodel

import android.widget.Toast
import androidx.databinding.ObservableField
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.RecyclerViewModel
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.eventbus.RxEventBus
import com.tsdev.tsandroid.provider.ResourceProvider
import com.tsdev.tsandroid.ui.observe.ObserverProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val movieRepository: NaverReopsitory,
    private val resourceProvider: ResourceProvider,
    override val observe: ObserverProvider,
    private val recyclerViewModel: RecyclerViewModel<Item>,
    private val rxEventBus: RxEventBus
) : BaseViewModel() {

    companion object {
        const val NON_QUERY = "N/A"
    }

    var isLoading = false

    val observerQuery = ObservableField<String>()

    private val compositeDisposable = CompositeDisposable()

    fun searchMovie() {
        compositeDisposable.add(
            movieRepository.getMovieList(observerQuery.get() ?: NON_QUERY)
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    it.printStackTrace()
                    resourceProvider.getResultErrorString(R.string.occur_error_toast)
                    emptyList()
                }.observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isLoading = true
                }
                .doOnTerminate { isLoading = false }
                .subscribe { items: List<Item>, _ ->
                    items.takeIf { list -> list.isNotEmpty() }
                        ?.apply {
                            observe.disconnectObserve()
                            this.forEach {
                                observe.observeList(it)
                            }
                            recyclerViewModel.notifiedDataChange()
                        }
                        ?: apply {
                            removeAll()
                            showToastMessage(resourceProvider.getResultErrorString(R.string.non_search_result))
                        }
                }
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(resourceProvider.getContext, message, Toast.LENGTH_LONG).show()
    }

    private fun removeAll() {
        observe.disconnectObserve()
        recyclerViewModel.notifiedDataChange()
    }

    override fun onBackKeyPressed() {
        rxEventBus.sendBackButtonEvent(System.currentTimeMillis())
    }
}