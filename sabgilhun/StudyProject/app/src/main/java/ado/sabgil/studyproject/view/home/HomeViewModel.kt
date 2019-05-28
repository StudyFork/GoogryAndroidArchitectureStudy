package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BaseViewModel
import kotlin.properties.Delegates

class HomeViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    val marketListListeners = mutableListOf<((List<String>?) -> Unit)?>()

    private var marketList: List<String>? by Delegates.observable(emptyList()) { _, _, newValue ->
        marketListListeners.map {
            it?.invoke(newValue)
        }
    }

    val showToastEventListeners = mutableListOf<((String) -> Unit)?>()

    val showProgressEventListeners = mutableListOf<(() -> Unit)?>()

    val hideProgressEventListeners = mutableListOf<(() -> Unit)?>()

    fun loadMarketList() {
        showProgressBar()

        coinRepository.loadMarketList(
            { response ->
                hideProgressBar()
                marketList = response
            }, { error ->
                hideProgressBar()
                showToast(error)
            }
        ).apply { disposables.add(this) }
    }

    private fun showToast(message: String) {
        showToastEventListeners.map { it?.invoke(message) }
    }

    private fun showProgressBar() {
        showProgressEventListeners.map { it?.invoke() }
    }

    private fun hideProgressBar() {
        hideProgressEventListeners.map { it?.invoke() }
    }
}