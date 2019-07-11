package org.study.kotlin.androidarchitecturestudy.view.activity.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseFragment
import org.study.kotlin.androidarchitecturestudy.base.BaseRecyclerView
import org.study.kotlin.androidarchitecturestudy.data.TickerRepository
import org.study.kotlin.androidarchitecturestudy.data.source.remote.TickerRemoteDataSource
import org.study.kotlin.androidarchitecturestudy.databinding.FragmentMainBinding
import org.study.kotlin.androidarchitecturestudy.databinding.ItemTickerBinding

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_mainfragment.run {
            setHasFixedSize(true)
            val message = arguments!!.getString(EXTRA_MESSAGE)

            binding.viewModel = MainViewModel(TickerRepository(TickerRemoteDataSource()), message)

            binding {
                adapter =
                    object : BaseRecyclerView.Adapter<TickerModel, ItemTickerBinding>(
                        layoutRes = R.layout.item_ticker,
                        bindingVariableId = BR.itemViewModel
                    ) {}
            }
        }
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

