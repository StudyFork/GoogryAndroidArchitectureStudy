package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_coinrecycler.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.model.UpbitItem

class UpbitFragment : Fragment(), UpbitContract.View {

    override lateinit var presenter: UpbitContract.Presenter
    private val rVAdapter = UpbitRVAdapter()

    override fun showAddTask(tickerList: List<UpbitItem>) {
        rVAdapter.setTickerList(tickerList)
    }

    //onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinrecycler, container, false)
    }


    //onActivityCreated 에서는 activity가 붙어있다고 확신할 수 있음 -> onViewCreted는 스쳐지나가는 정도임
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        CoinRecyclerView.apply {
            adapter = rVAdapter
            layoutManager = LinearLayoutManager(context!!)
            setHasFixedSize(true)
        }


        //TODO : MARKET_NAME enum으로 빼자
        val marketName = arguments?.get("MARKET_NAME").toString()
        presenter = UpbitPresenter(Repository, this)
        presenter.start(marketName)
    }

    companion object {
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString("MARKET_NAME", marketName)
            fragment.arguments = bundle
            return fragment
        }
    }

}