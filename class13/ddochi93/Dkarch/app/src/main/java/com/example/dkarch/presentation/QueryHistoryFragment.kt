package com.example.dkarch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.dkarch.databinding.FragmentQueryHistoryBinding
import com.example.dkarch.domain.globalconsts.Consts


class QueryHistoryFragment : DialogFragment() {
    private lateinit var queryHistoryBinding: FragmentQueryHistoryBinding
    private var queryHistory: ArrayList<String> = ArrayList()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        (activity as MainActivity).getMovieList(query)
    }

    companion object {
        //        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            QueryHistoryFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}
