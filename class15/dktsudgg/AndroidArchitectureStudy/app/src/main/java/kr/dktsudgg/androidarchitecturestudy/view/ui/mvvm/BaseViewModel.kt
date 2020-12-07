package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    /**
     * 뷰모델에 데이터 갱신 요청 시, 파라미터 값이 비어있는 경우에 알리기 위한 변수
     */
    val eventWhenEmptyInputInjected = MutableLiveData<Unit>()

    /**
     * 뷰모델에 데이터 갱신 요청 성공 시, 알리기 위한 변수
     */
    val eventWhenDataRefreshRequestSuccess = MutableLiveData<Unit>()

    /**
     * 뷰모델에 데이터 갱신 요청 실패 시, 알리기 위한 변수
     */
    val eventWhenDataRefreshRequestFailure = MutableLiveData<Unit>()

    fun isEmptyData(data: String): Boolean {
        return data == null || data == ""
    }

}