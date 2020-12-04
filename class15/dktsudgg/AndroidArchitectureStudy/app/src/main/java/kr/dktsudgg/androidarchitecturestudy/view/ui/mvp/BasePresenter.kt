package kr.dktsudgg.androidarchitecturestudy.view.ui.mvp

abstract class BasePresenter : BaseContract.Presenter {
    override fun isEmptyData(data: String): Boolean {
        return data == null || data == ""
    }

}