package dev.daeyeon.gaasproject.ui.ticker

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.*
import android.view.inputmethod.EditorInfo
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
    private lateinit var tickerAdapter: TickerAdapter

    private val searchDialogBinding by lazy { initDialogTickerSearchBinding() }
    // 검색 다이얼로그
    private var searchDialog: AlertDialog? = null

    private val tickerViewModel by lazy { TickerViewModel(UpbitRepository(NetworkManager.instance)) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tickerAdapter = TickerAdapter()
        binding.rvTicker.adapter = tickerAdapter

        setHasOptionsMenu(true)
        swipeInit()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_ticker_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
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
     * ticker 검색 다이얼로그
     */
    private fun showTickerSearchDialog() {

        // 기존 부모뷰에 붙은 자식뷰 제거
        if (searchDialogBinding.root.parent != null) {
            (searchDialogBinding.root.parent as ViewGroup).removeView(searchDialogBinding.root)
        }

        searchDialog = AlertDialog.Builder(activity!!)
            .setTitle(R.string.ticker_search)
            .setView(searchDialogBinding.root)
            .setPositiveButton(R.string.all_positive) { _, _ ->
                tickerViewModel.loadUpbitTicker(searchDialogBinding.etSearch.text.toString())
                searchDialogBinding.etSearch.setText("")
            }
            .setNegativeButton(R.string.all_negative) { _, _ -> }
            .create()
        searchDialog?.show()
    }

    /**
     * 커스텀 레이아웃 다이얼로그를 사용하기 위한 DialogTickerSearchBinding
     *
     */
    private fun initDialogTickerSearchBinding(): DialogTickerSearchBinding {
        val dialogTickerSearchBinding = DataBindingUtil.inflate<DialogTickerSearchBinding>(
            activity!!.layoutInflater,
            R.layout.dialog_ticker_search,
            null,
            false
        )

        bind(dialogTickerSearchBinding) {
            btnTickerSearch.setOnClickListener {
                searchDialog?.dismiss()
                tickerViewModel.loadUpbitTicker(etSearch.text.toString())
                etSearch.setText("")
            }

            etSearch.setOnEditorActionListener { _, actionId, _ ->
                // 키보드의 검색 키
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    btnTickerSearch.performClick()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }

        return dialogTickerSearchBinding
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

    override fun onResume() {
        super.onResume()
        tickerViewModel.loadUpbitTicker()
    }

    override fun onDestroyView() {
        tickerViewModel.cancelApi()
        super.onDestroyView()
    }

    /**
     * swipeRefreshLayout 버튼 색, 이벤트 설정
     */
    private fun swipeInit() {
        binding.srlTicker.apply {
            setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
            setOnRefreshListener { tickerViewModel.loadUpbitTicker() }
        }
    }

    fun toastTickerFailMsg(msg: String) {
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
