package study.architecture.myarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import study.architecture.myarchitecture.base.BaseViewModel
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.util.Filter
import timber.log.Timber

class MainViewModel(
    private val upbitRepository: UpbitRepository
) : BaseViewModel() {

    private val _titles = MutableLiveData<Array<String>>()
    private val _markets = MutableLiveData<Array<String>>()

    private val _pageLimit = MutableLiveData<Int>()

    private val _category = MutableLiveData<Filter.SelectArrow>(Filter.SelectArrow.COIN_NAME)
    private val _isSelected = MutableLiveData<Boolean>(true)

    private val _selectField = MutableLiveData<Pair<Filter.SelectArrow, Boolean>>()

    val titles: LiveData<Array<String>> get() = _titles
    val markets: LiveData<Array<String>> get() = _markets

    val pageLimit: LiveData<Int> get() = _pageLimit

    val category: LiveData<Filter.SelectArrow> get() = _category
    val isSelected: LiveData<Boolean> get() = _isSelected

    val selectField: LiveData<Pair<Filter.SelectArrow, Boolean>> get() = _selectField

    init {
        loadData()
    }

    private fun loadData() {

        addDisposable(
            upbitRepository.getGroupedMarkets()
                .subscribe({ groupMarket ->

                    val keys = groupMarket.keys

                    _titles.value = keys.toTypedArray()
                    _pageLimit.value = keys.size

                    val arrMarkets = Array(keys.size) { "" }

                    for ((index, value) in keys.withIndex()) {

                        arrMarkets[index] = groupMarket
                            .getValue(value)
                            .joinToString(separator = ",") { it.market }
                    }

                    _markets.value = arrMarkets

                }) {
                    Timber.e(it)
                }
        )
    }

    fun showCategoryArrow(selectArrow: Filter.SelectArrow) {

        _category.value = selectArrow

        val selected = !isSelected.value!!
        _isSelected.value = selected

        _selectField.value = Pair(selectArrow, selected)
    }
}