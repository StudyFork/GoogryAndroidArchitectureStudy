package study.architecture

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import study.architecture.adapter.CoinDataAdapter
import study.architecture.model.DataParser


@SuppressLint("ValidFragment")
class MainFragment(private val idx: Int) : Fragment() {
    internal lateinit var view: View
    private lateinit var adapter: CoinDataAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::view.isInitialized) {
            view = inflater.inflate(R.layout.fragment_main, container, false)
            view.recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.HORIZONTAL, false)
            adapter = CoinDataAdapter(DataParser.tickerList[idx])
            view.recyclerView.adapter = adapter
        }
        return view
    }


    companion object {
        const val url = "https://api.upbit.com/v1/"
    }

}