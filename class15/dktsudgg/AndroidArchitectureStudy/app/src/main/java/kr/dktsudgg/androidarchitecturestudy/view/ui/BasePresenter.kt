package kr.dktsudgg.androidarchitecturestudy.view.ui

abstract class BasePresenter : BaseContract.Presenter {
    override fun isEmptyData(data: String): Boolean {
        return data == null || data == ""
    }

}