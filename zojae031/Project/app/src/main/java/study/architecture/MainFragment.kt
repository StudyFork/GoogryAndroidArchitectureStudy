package study.architecture

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import study.architecture.adapter.CoinDataAdapter

@SuppressLint("ValidFragment")
class MainFragment<TAB>(private val state: TAB) : Fragment() {
    internal lateinit var view: View
    private val adapter = CoinDataAdapter()
    private var list: List<Int> = listOf(0, 0, 0, 0)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::view.isInitialized) {
            view = inflater.inflate(R.layout.fragment_main, container, false)
            view.recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.HORIZONTAL, false)
            view.recyclerView.adapter = adapter
        }

        when (state) {
            MainActivity.Companion.TAB.KRW -> list = listOf(1, 1, 1, 1)
            MainActivity.Companion.TAB.BTC -> list = listOf(2, 2, 2, 2)
            MainActivity.Companion.TAB.ETH -> list = listOf(3, 3, 3, 3)
            MainActivity.Companion.TAB.USDT -> list = listOf(4, 4, 4, 4)
        }
        adapter.setList(list)

        return view
    }


}