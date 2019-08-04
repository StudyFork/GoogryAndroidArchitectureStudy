package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_coinrecycler.view.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.model.UpbitItem

class UpbitFragment : Fragment(), UpbitContract.View {

    override lateinit var presenter: UpbitContract.Presenter
    private val rVAdapter = UpbitRVAdapter()


    override fun showAddTask(tickerList: MutableList<UpbitItem>) {
        rVAdapter.setTickerList(tickerList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinrecycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val coinRecyclerView = view.CoinRecyclerView

        coinRecyclerView.adapter = rVAdapter

        val lm = LinearLayoutManager(context!!)
        coinRecyclerView.layoutManager = lm
        coinRecyclerView.setHasFixedSize(true)


        val marketName = arguments?.get("MARKET_NAME").toString()
        presenter.start(marketName)
    }



    companion object {

        fun newInstance() = UpbitFragment()
    }
}