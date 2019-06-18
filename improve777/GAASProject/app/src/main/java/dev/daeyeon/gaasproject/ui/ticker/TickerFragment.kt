package dev.daeyeon.gaasproject.ui.ticker

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.base.BaseFragment
import dev.daeyeon.gaasproject.data.response.ResponseCode
import dev.daeyeon.gaasproject.data.source.UpbitRepository
import dev.daeyeon.gaasproject.databinding.DialogTickerSearchBinding
import dev.daeyeon.gaasproject.databinding.FragmentTickerBinding
import dev.daeyeon.gaasproject.network.NetworkManager
import org.jetbrains.anko.toast

class TickerFragment : BaseFragment<FragmentTickerBinding>(
    R.layout.fragment_ticker
) {
    private val searchDialogBinding by lazy { initDialogTickerSearchBinding() }
    // 검색 다이얼로그
    private var searchDialog: AlertDialog? = null

    private val tickerViewModel by createViewModelLazy(
        viewModelClass = TickerViewModel::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return TickerViewModel(UpbitRepository(NetworkManager.instance)) as T
                }
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind {
            rvTicker.adapter = TickerAdapter()
            viewModel = tickerViewModel
            lifecycleOwner = this@TickerFragment.viewLifecycleOwner
        }

        setHasOptionsMenu(true)
        swipeInit()

        subscribeToFailMsg()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_ticker_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 검색
            R.id.action_search -> {
                showTickerSearchDialog()
            }
            // 기본 통화 설정
            R.id.action_base_currency -> {
                showBaseCurrencyDialog()
            }
        }

        return false
    }

    /**
     * 커스텀 레이아웃 다이얼로그를 사용하기 위한 DialogTickerSearchBinding
     *
     */
    private fun initDialogTickerSearchBinding(): DialogTickerSearchBinding {
        return DataBindingUtil.inflate<DialogTickerSearchBinding>(
            activity!!.layoutInflater,
            R.layout.dialog_ticker_search,
            null,
            false
        ).apply {
            viewModel = tickerViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    /**
     * ticker 검색 다이얼로그
     */
    private fun showTickerSearchDialog() {
        if (searchDialog == null) {
            initSearchDialog()
        }
        searchDialog?.show()
    }

    private fun initSearchDialog() {
        searchDialog = AlertDialog.Builder(activity!!)
            .setTitle(R.string.ticker_search)
            .setView(searchDialogBinding.root)
            .setPositiveButton(R.string.all_positive) { _, _ ->
                tickerViewModel.loadUpbitTicker()
            }
            .setNegativeButton(R.string.all_negative) { _, _ -> }
            .create()
    }

    /**
     * 기본 통화 설정 다이얼로그
     */
    private fun showBaseCurrencyDialog() {
        AlertDialog.Builder(activity!!)
            .setTitle(R.string.ticker_fragment_base_currency)
            .setSingleChoiceItems(
                tickerViewModel.getCurrencyArray(),
                tickerViewModel.getCurrencyArray().indexOf(tickerViewModel.getBaseCurrency())
            ) { _, position ->
                tickerViewModel.setBaseCurrency(tickerViewModel.getCurrencyArray()[position])
            }
            .setPositiveButton(R.string.all_positive) { _, _ ->
                tickerViewModel.loadUpbitTicker()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    /**
     * swipeRefreshLayout 버튼 색, 이벤트 설정
     */
    private fun swipeInit() {
        binding.srlTicker.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    /**
     * failMsgEvent 구독
     */
    private fun subscribeToFailMsg() {
        tickerViewModel.failMsgEvent.observe(this, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                toastTickerFailMsg(it)
            }
        })
    }

    private fun toastTickerFailMsg(msg: String) {
        when (msg) {
            ResponseCode.CODE_NULL_SUCCESS,
            ResponseCode.CODE_NULL_FAIL_MSG -> activity!!.toast(R.string.all_network_error)
            ResponseCode.CODE_EMPTY_SUCCESS -> activity!!.toast(R.string.ticker_fragment_empty)
            else -> activity!!.toast(msg)
        }
    }

    companion object {
        fun newInstance(): TickerFragment {
            return TickerFragment()
        }
    }
}
