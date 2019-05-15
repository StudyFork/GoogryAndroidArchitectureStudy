package me.hoyuo.myapplication.ui.coinlist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_coin_list.*
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.coindetail.CoinDetailActivity
import me.hoyuo.myapplication.ui.coinlist.adapter.ItemAdapter
import timber.log.Timber

class CoinListFragment : Fragment(), CoinListContract.View {
    override lateinit var presenter: CoinListContract.Presenter
    private lateinit var itemAdapter: ItemAdapter

    //region LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG).d("onCreate")
        super.onCreate(savedInstanceState)
        presenter = CoinListPresenter(this)
    }

    override fun onDestroy() {
        Timber.tag(TAG).d("onDestroy")
        super.onDestroy()
    }

    override fun onStart() {
        Timber.tag(TAG).d("onStart")
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        Timber.tag(TAG).d("onStop")
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onResume() {
        Timber.tag(TAG).d("onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.tag(TAG).d("onPause")
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_coin_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        presenter.start()

        itemAdapter = ItemAdapter().apply {
            setItemClickListener {
                presenter.onItemClick(it)
            }
        }

        itemListView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onAttach(context: Context?) {
        Timber.tag(TAG).d("onAttach")
        super.onAttach(context)
    }
    //endregion

    //region Widget
    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
    //endregion

    //region View
    override fun updateData(list: List<Ticker>) {
        hideProgressBar()
        Timber.tag(TAG).d("updateData - ${list.size}")
        itemAdapter.loadData(list)
    }

    override fun navigationCoinDetailActivity(ticker: Ticker) {
        val intent = activity?.baseContext?.let { CoinDetailActivity.newIntent(it, ticker) }
        startActivity(intent)
    }
    //endregion

    companion object {
        val TAG: String = CoinListFragment::class.java.simpleName

        fun newIntent() = CoinListFragment()
    }
}
