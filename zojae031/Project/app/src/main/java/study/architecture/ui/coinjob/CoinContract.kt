package study.architecture.ui.coinjob

import study.architecture.ui.coinjob.adapter.CoinAdapterContract

interface CoinContract {
    interface View {
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onResume()
        fun onPause()
        fun setAdapterView(adapterView: CoinAdapterContract.View)
        fun setAdapterModel(adapterModel: CoinAdapterContract.Model)
    }
}