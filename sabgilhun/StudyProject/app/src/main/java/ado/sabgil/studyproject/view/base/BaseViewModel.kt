package ado.sabgil.studyproject.view.base

import ado.sabgil.studyproject.utils.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    private val _showToastEvent = SingleLiveEvent<String>()
    val showToastEvent: LiveData<String>
        get() = _showToastEvent

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    override fun onCleared() {
        disposables.clear()
    }

    protected fun handleErrorMessage(message: String) {
        _showToastEvent.value = message
    }

    protected fun handleErrorMessage(throwable: Throwable) {
        throwable.printStackTrace()
        _showToastEvent.value = "서버에서 데이터를 가져오는데에 실패하였습니다."
    }

    protected fun startLoading() {
        _isLoading.value = true
    }

    protected fun endLoading() {
        _isLoading.value = false
    }

}