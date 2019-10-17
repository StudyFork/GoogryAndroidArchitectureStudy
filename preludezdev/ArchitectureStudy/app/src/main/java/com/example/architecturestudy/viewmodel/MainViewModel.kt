package com.example.architecturestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.util.Filter

class MainViewModel : ViewModel() {
    private val _filter = MutableLiveData<Pair<Filter.Type, Filter.Order>>()
    val filter: LiveData<Pair<Filter.Type, Filter.Order>> get() = _filter

    private val _type = MutableLiveData(Filter.Type.ACC_TRADE_PRICE_H)
    val type: LiveData<Filter.Type> get() = _type

    private val _order = MutableLiveData(Filter.Order.ASC)
    val order: LiveData<Filter.Order> get() = _order

    private val _isSelected = MutableLiveData(true)
    val isSelected: LiveData<Boolean> get() = _isSelected

    private val _isReload = MutableLiveData(true)
    val isReload: LiveData<Boolean> get() = _isReload

    fun showSortedTypeArrow(type: Filter.Type) {
        when (type) {
            _type.value -> {
                _isSelected.value = !(isSelected.value)!!
                _order.value =
                    if (order.value == Filter.Order.ASC) Filter.Order.DESC else Filter.Order.ASC
            }
            else -> {
                _type.value = type
            }
        }

        _filter.value = Pair(_type.value!!, _order.value!!)
    }

    fun reloadData() {
        _isReload.value = true
    }
}