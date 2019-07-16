package study.architecture.mainjob

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import study.architecture.R
import study.architecture.adapter.CoinDataAdapter
import study.architecture.vo.Ticker


@SuppressLint("ValidFragment", "WrongConstant")
class MainFragment(idx: FragIndex) : Fragment(), MainContract.View {
    private val presenter = MainPresenter(this@MainFragment, idx)

    private val adapter: CoinDataAdapter = CoinDataAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false).apply {
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }


    override fun notifyAdapter(list: List<Ticker>) {
        adapter.lists = list
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    enum class FragIndex {
        KRW, BTC, ETH, USDT
    }
}