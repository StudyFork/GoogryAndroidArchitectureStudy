package study.architecture

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

@SuppressLint("ValidFragment")
class MainFragment(private val state: Int) : Fragment() {
    internal lateinit var view: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::view.isInitialized) {
            view = inflater.inflate(R.layout.fragment_main, container, false)
        }
        when (state) {

        }
        return view
    }

    enum class TAB {
        KRW, BTC, ETH, USDT
    }
}