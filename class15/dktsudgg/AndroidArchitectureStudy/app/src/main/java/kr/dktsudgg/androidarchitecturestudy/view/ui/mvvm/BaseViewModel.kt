package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.databinding.ObservableField

abstract class BaseViewModel {

    /**
     * 뷰모델에 데이터 갱신 요청 시, 파라미터 값이 비어있는 경우에 알리기 위한 변수
     */
    val eventWhenEmptyInputInjected = ObservableField<Unit>()

    /**
     * 뷰모델에 데이터 갱신 요청 성공 시, 알리기 위한 변수
     */
    val eventWhenDataRefreshRequestSuccess = ObservableField<Unit>()

    /**
     * 뷰모델에 데이터 갱신 요청 실패 시, 알리기 위한 변수
     */
    val eventWhenDataRefreshRequestFailure = ObservableField<Unit>()

    fun isEmptyData(data: String): Boolean {
        return data == null || data == ""
    }

}