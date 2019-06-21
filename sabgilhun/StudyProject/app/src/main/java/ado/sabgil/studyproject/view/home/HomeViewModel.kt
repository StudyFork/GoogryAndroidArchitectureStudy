package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.enums.SortingCriteria
import ado.sabgil.studyproject.view.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo

class HomeViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    private val _marketList = MutableLiveData<List<String>>()
    val marketList: LiveData<List<String>>
        get() = _marketList

    private val _selectedSortField = MutableLiveData<SortingCriteria>(SortingCriteria.COIN_NAME)
    val selectedSortField: LiveData<SortingCriteria>
        get() = _selectedSortField

    private val _sortingOrder = MutableLiveData<Boolean>(true)
    val sortingOrder: LiveData<Boolean>
        get() = _sortingOrder

    fun selectSortField(sortingCriteria: SortingCriteria) {
        if (_selectedSortField.value == sortingCriteria) {
            toggleSortingOrder()
        } else {
            _selectedSortField.value = sortingCriteria
            _sortingOrder.value = false
        }
    }

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

    private fun toggleSortingOrder() {
        _sortingOrder.value = _sortingOrder.value?.not()
    }
}