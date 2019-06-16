package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo

class HomeViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    private val _marketList = MutableLiveData<List<String>>()
    val marketList: LiveData<List<String>>
        get() = _marketList

    fun loadMarketList() {
        startLoading()

        coinRepository.loadMarketList(
            { response ->
                endLoading()
                _marketList.value = response
            }, { error ->
                endLoading()
                handleErrorMessage(error)
            }
        ).addTo(disposables)
    }
}