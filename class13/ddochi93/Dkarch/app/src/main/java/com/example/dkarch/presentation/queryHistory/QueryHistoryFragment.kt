package com.example.dkarch.presentation.queryHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.dkarch.databinding.FragmentQueryHistoryBinding
import com.example.dkarch.domain.api.usecase.GetMovieListUseCase
import com.example.dkarch.domain.repositoryImpl.HttpClientRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.NaverMovieRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.RetrofitRepositoryImpl
import com.example.dkarch.presentation.base.BaseDialogFragment
import com.example.dkarch.presentation.main.MainActivity


class QueryHistoryFragment : BaseDialogFragment<QueryHistoryContract.Presenter>(),
    QueryHistoryContract.View {
    private lateinit var queryHistoryBinding: FragmentQueryHistoryBinding
    override val presenter by lazy {
        QueryHistoryPresenter(
            this,
            NaverMovieRepositoryImpl(
                GetMovieListUseCase(
                    RetrofitRepositoryImpl(HttpClientRepositoryImpl())
                )
            )
        )
    }

    interface QuerySelectedListener {
        fun onQuerySelected(query: String)
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
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
        presenter.getSavedQueryList()
    }

    fun closeButtonClicked() {
        dismiss()
    }

    private val onQueryItemClicked = { query: String ->
        dismiss()
        (activity as MainActivity).onQuerySelected(query)
    }

    override fun showQueryList(savedQueryList: List<String>) {
        if (savedQueryList.isEmpty()) {
            queryHistoryBinding.emptyListText.visibility = View.VISIBLE
        } else {
            queryHistoryBinding.emptyListText.visibility = View.GONE
            queryHistoryBinding.queryRecyclerView.adapter =
                QueryHistoryAdapter(savedQueryList.reversed(), onQueryItemClicked)
        }
    }
}
