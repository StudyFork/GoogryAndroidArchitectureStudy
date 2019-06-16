package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo

class CoinListViewModel(
    private val baseCurrency: String,
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    private val _coinList = MutableLiveData<List<Ticker>>()
    val coinList: LiveData<List<Ticker>>
        get() = _coinList

    fun subscribeRemote() {
        startLoading()
        coinRepository.subscribeCoinDataByCurrency(
            baseCurrency,
            { response ->
                endLoading()
                _coinList.value = response
            }, { error ->
                endLoading()
                handleErrorMessage(error)
            }
        ).addTo(disposables)
    }

    fun unSubscribeRemote() {
        coinRepository.unSubscribeCoinData()
        disposables.clear()
    }
}