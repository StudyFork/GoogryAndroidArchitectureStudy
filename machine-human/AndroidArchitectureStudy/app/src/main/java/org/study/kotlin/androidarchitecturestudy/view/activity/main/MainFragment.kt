package org.study.kotlin.androidarchitecturestudy.view.activity.main

import androidx.lifecycle.Observer
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.base.BaseFragment
import org.study.kotlin.androidarchitecturestudy.data.TickerRepository
import org.study.kotlin.androidarchitecturestudy.data.source.remote.TickerRemoteDataSource
import org.study.kotlin.androidarchitecturestudy.databinding.FragmentMainBinding

/**
 ***************************
Contract - structure

i = interface
f = function
v = variable
 ***************************

i = MainContract

i = View : BaseView<Presenter>
f = setTickers(tickers: List<TickerModel>)

i = Presenter : BasePresenter

f = requestDataFromTickerRepository(marketName: String)
 */
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainListAdapter: MainListAdapter = MainListAdapter()

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        view.recyclerview_mainfragment.run {
//            setHasFixedSize(true)
//            val message = arguments!!.getString(EXTRA_MESSAGE)
//
//            binding.viewModel = MainViewModel(TickerRepository(TickerRemoteDataSource()), message)
//            adapter = mainListAdapter
//        }
//
//        binding.viewModel?.observableErrorMessage?.observe(this, Observer {
//            errorMessage -> errorMessage?.let { showErrorToast(it) }
//        })
//
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_mainfragment.run {
            setHasFixedSize(true)
            val message = arguments!!.getString(EXTRA_MESSAGE)

//            binding.viewModel = MainViewModel(TickerRepository(TickerRemoteDataSource()), message)
            binding.viewModel = MainViewModel(TickerRepository(TickerRemoteDataSource()), message)
            adapter = mainListAdapter
        }

        binding.viewModel?.observableErrorMessage?.observe(this, Observer { errorMessage ->
            errorMessage?.let { showErrorToast(it) }
        })
    }

    override fun showErrorToast(errorMessage: Throwable) {
        super.showErrorToast(errorMessage)
        Toast.makeText(context, errorMessage.toString(), Toast.LENGTH_SHORT).show()
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

}

