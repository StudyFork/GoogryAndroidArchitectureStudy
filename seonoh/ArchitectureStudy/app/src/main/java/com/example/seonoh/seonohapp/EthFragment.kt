package com.example.seonoh.seonohapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
class EthFragment : Fragment() {

    lateinit var mView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.eth_fragment, container, false)
        return mView
    }

    companion object{
        fun newInstance(): EthFragment {
            return EthFragment()
        }
    }

}
