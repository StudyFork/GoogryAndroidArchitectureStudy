package org.study.kotlin.androidarchitecturestudy.view.activity.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseFragment
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
class MainFragment : BaseFragment(R.layout.fragment_main), MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private val mainListAdapter: MainListAdapter = MainListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recyclerview_mainfragment.run {
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
        val message = arguments!!.getString(EXTRA_MESSAGE)
        MainPresenter(
            this,
            TickerRepository(TickerRemoteDataSource())
            , message
        )
    }


    companion object {
        fun createInstance(message: String): MainFragment {
            val fragmentInstance = MainFragment()
            val bundle = Bundle(1)
            bundle.putString(EXTRA_MESSAGE, message)
            fragmentInstance.arguments = bundle
            return fragmentInstance
        }
    }


    //MainContract.View의 함수
    override fun setTickers(tickers: ArrayList<TickerModel>) {
        mainListAdapter?.setList(tickers)
        mainListAdapter?.notifyDataSetChanged()
    }

    //BaseView의 함수 (기능 추가 예정)
    override fun showProgress(text: Throwable) {

    }

    //BaseView의 함수 (기능 추가 예정)
    override fun hideProgress() {

    }

}

