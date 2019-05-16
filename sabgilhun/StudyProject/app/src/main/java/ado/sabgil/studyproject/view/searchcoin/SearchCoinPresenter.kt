package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BasePresenter

class SearchCoinPresenter(
    private val searchCoinView: SearchCoinContract.View,
    private val coinRepository: CoinRepository
) : BasePresenter<SearchCoinContract.View>(searchCoinView), SearchCoinContract.Presenter {

    private var currentCoin = ""

    override fun searchCoin(coinName: String) {

        if(disposables.size() > 0) {
            disposables.clear()
        }

        disposables.add(coinRepository
            .getCoinDataChangeWithCoinName(
                coinName.toUpperCase(), {
                    if (it.isNotEmpty()) {
                        currentCoin = coinName
                        searchCoinView.updateCoinList(it)
                    } else {
                        disposables.clear()
                        searchCoinView.showToastMessage("$coinName 의 검색결과가 없습니다.")
                    }
                }, {
                    searchCoinView.showToastMessage(it)
                })
        )
    }

    override fun subscribeCoinData() {
        if (disposables.size() == 0 && currentCoin != "") {
            searchCoin(currentCoin)
        }
    }

    override fun unSubscribeCoinData() {
        disposables.clear()
    }
}