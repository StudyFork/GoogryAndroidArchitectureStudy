package study.architecture.myarchitecture.ui.tickerlist

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.support.v4.toast
import study.architecture.myarchitecture.BaseFragment
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.databinding.FragmentTickerListBinding
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.util.Filter

class TickerListFragment : BaseFragment<FragmentTickerListBinding>(R.layout.fragment_ticker_list),
    TickerListContract.View {

    private val tickerAdapter by lazy {
        TickerAdapter { toast(it.toString()) }
    }

    private lateinit var presenter: TickerListContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let { bundle ->

            presenter = TickerListPresenter(
                Injection.provideFolderRepository(context!!),
                this@TickerListFragment,
                bundle.getString(KEY_MARKETS, "")
            )

            initRecyclerView()
            presenter.loadData()

        } ?: error("arguments is null")

    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.rvTickerList.adapter = tickerAdapter
    }

    override fun showProgress() {
        binding.pbTickerList.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.pbTickerList.visibility = View.GONE
    }

    override fun showTickers(tickers: MutableList<TickerItem>) {
        tickerAdapter.setItems(tickers)
    }

    override fun showTickerListOrderByField(field: Filter.SelectArrow, order: Int) {
        presenter.sortByField(field, order)
    }

    companion object {

        const val KEY_MARKETS = "markets"

        fun newInstance(tickers: String) = TickerListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKETS, tickers)
            }
        }
    }
}