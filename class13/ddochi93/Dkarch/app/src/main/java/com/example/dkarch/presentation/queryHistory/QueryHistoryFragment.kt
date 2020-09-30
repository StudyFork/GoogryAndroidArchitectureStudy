package com.example.dkarch.presentation.queryHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.dkarch.databinding.FragmentQueryHistoryBinding
import com.example.dkarch.domain.globalconsts.Consts
import com.example.dkarch.presentation.base.BaseDialogFragment
import com.example.dkarch.presentation.main.MainActivity


class QueryHistoryFragment : BaseDialogFragment<QueryHistoryContract.Presenter>(),
    QueryHistoryContract.View {
    private lateinit var queryHistoryBinding: FragmentQueryHistoryBinding
    private var queryHistory: ArrayList<String> = ArrayList()
    override val presenter by lazy {
        QueryHistoryPresenter(this)
    }

    interface QuerySelectedListener {
        fun onQuerySelected(query: String)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return queryHistoryBinding.root
    }


    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        queryHistoryBinding = FragmentQueryHistoryBinding.inflate(inflater, container, false)
        queryHistoryBinding.fragment = this
    }

    private fun initView() {
        arguments?.getStringArrayList(Consts.FRAGMENT_QUERY_LIST)?.map {
            queryHistory.add(it)
        }
        queryHistoryBinding.queryRecyclerView.adapter =
            QueryHistoryAdapter(queryHistory.reversed(), onQueryItemClicked)

        if (queryHistory.isEmpty()) {
            queryHistoryBinding.emptyListText.visibility = View.VISIBLE
        } else {
            queryHistoryBinding.emptyListText.visibility = View.GONE
        }

    }

    fun closeButtonClicked() {
        dismiss()
    }

    private val onQueryItemClicked = { query: String ->
        dismiss()
        (activity as MainActivity).onQuerySelected(query)
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}
