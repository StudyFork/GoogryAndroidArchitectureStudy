package org.study.kotlin.androidarchitecturestudy.view.fragment.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.data.TickerRepository
import org.study.kotlin.androidarchitecturestudy.data.source.remote.TickerRemoteDataSource

/**
***************************
Contract - structure

i = interface
f = function
v = variable
***************************

i = MainContract

    i = View : BaseView<Presenter>
    f = setTickers(tickers: ArrayList<TickerModel>)

    i = Presenter : BasePresenter

    f = requestDataFromTickerRepository(marketName: String)
*/
class MainFragment : Fragment(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private var mainListAdapter: MainListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater.inflate(R.layout.fragment_main, container, false)
        view?.recyclerview_mainfragment?.setHasFixedSize(true)
        Log.e("TAG MainFragment", "onCreateView")


        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        view?.recyclerview_mainfragment?.layoutManager = layoutManager
        mainListAdapter = MainListAdapter()
        view?.recyclerview_mainfragment?.adapter = mainListAdapter
        var mainPresnter = MainPresenter(
            this,
            TickerRepository(TickerRemoteDataSource)
        )
        mainPresnter.start()

        val message = arguments!!.getString(EXTRA_MESSAGE)
        presenter.requestDataFromTickerRepository(message)

        return view
    }


    companion object {
        fun createInstance(message: String): MainFragment {
            Log.e("TAG MainFragment", "createInstance")
            val fragmentInstance = MainFragment()
            val bundle = Bundle(1)
            bundle.putString(EXTRA_MESSAGE, message)
            fragmentInstance.arguments = bundle
            return fragmentInstance
        }
    }


    //MainContract.View의 함수
    override fun setTickers(tickers: ArrayList<TickerModel>) {
        Log.e("TAG MainFragment", "setTickers")
        mainListAdapter?.setList(tickers)
        mainListAdapter?.notifyDataSetChanged()
    }

    //BaseView의 함수 (기능 추가 예정)
    override fun showProgress(text: String) {
        Log.e("TAG MainFragment", "showProgress")

    }

    //BaseView의 함수 (기능 추가 예정)
    override fun hideProgress() {
        Log.e("TAG MainFragment", "hideProgress")

    }

}

