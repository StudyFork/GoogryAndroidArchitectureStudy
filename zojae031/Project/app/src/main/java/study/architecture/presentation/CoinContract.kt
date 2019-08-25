package study.architecture.presentation

interface CoinContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError(e: String?)
    }

    interface Presenter {
        fun onResume()
        fun onPause()
        fun setAdapterView(adapterView: CoinAdapterContract.View)
        fun setAdapterModel(adapterModel: CoinAdapterContract.Model)
    }
}