package study.architecture.ui.mainjob

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import study.architecture.R
import study.architecture.model.vo.ProcessingTicker
import study.architecture.ui.adapter.CoinDataAdapter


@SuppressLint("ValidFragment", "WrongConstant")
class MainFragment : Fragment(), MainContract.View {

    private val presenter by lazy {
        MainPresenter(
            this@MainFragment,
            arguments!!.getSerializable("idx") as FragIndex
        )
    }

    private val adapter by lazy { CoinDataAdapter() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false).apply {
            recyclerView.adapter = adapter
            loading.animation = AnimationUtils.loadAnimation(context, R.anim.loading)
        }


    override fun notifyAdapter(list: List<ProcessingTicker>) {
        adapter.updateList(list)
        adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        loading.visibility = View.VISIBLE
        loading.animation.start()

    }

    override fun hideProgress() {
        loading.visibility = View.INVISIBLE
        loading.animation?.cancel()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    enum class FragIndex {
        KRW, BTC, ETH, USDT
    }
}