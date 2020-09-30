package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl
import com.hjhan.hyejeong.databinding.DialogQueryHistoryBinding


class QueryHistoryDialog : DialogFragment(), QueryHistoryContract.View {

    private lateinit var binding: DialogQueryHistoryBinding
    private lateinit var queryHistoryAdapter: QueryHistoryAdapter

    private val presenter: QueryHistoryContract.Presenter by lazy {
        val remoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val localDataSourceImpl = NaverLocalDataSourceImpl()
        QueryHistoryPresenter(
            this@QueryHistoryDialog,
            NaverRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setInflateLayout(inflater, container)
        initView()

        return binding.root
    }

    private fun setInflateLayout(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DialogQueryHistoryBinding.inflate(inflater, container, false)
        binding.dialog = this
    }

    private fun initView() {
        queryHistoryAdapter = QueryHistoryAdapter()
        binding.queryRecyclerView.adapter = queryHistoryAdapter

        presenter.getRecentQueryList()
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun emptyQueryList() {
        //목록 없을때
        binding.queryRecyclerView.visibility = View.GONE
        binding.emptyListText.visibility = View.VISIBLE
    }

    override fun setRecentQueryList(list: List<String>) {
        //목록 있을떄
        binding.queryRecyclerView.visibility = View.VISIBLE
        binding.emptyListText.visibility = View.GONE

        queryHistoryAdapter.setMovieList(list)
    }

    fun onCloseButtonClicked() {
        dismiss()
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}