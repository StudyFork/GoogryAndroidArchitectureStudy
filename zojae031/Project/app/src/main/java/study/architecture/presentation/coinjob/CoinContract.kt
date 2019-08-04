package study.architecture.presentation.coinjob

import study.architecture.presentation.coinjob.adapter.CoinAdapterContract

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