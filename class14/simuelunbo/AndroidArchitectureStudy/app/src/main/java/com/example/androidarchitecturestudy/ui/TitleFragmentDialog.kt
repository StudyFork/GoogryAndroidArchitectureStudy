package com.example.androidarchitecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import com.example.androidarchitecturestudy.databinding.TitleDialogFragmentBinding

class TitleFragmentDialog : DialogFragment(), TitleHistoryContract.View {

    private lateinit var binding: TitleDialogFragmentBinding
    private val adapter by lazy {
        TitleAdapter()
    }

    private val presenter: TitleHistoryContract.Presenter by lazy {
        val naverLocalDataSourceImpl = NaverLocalDataSourceImpl()
        val naverRemoteDataSourceImpl = NaverRemoteDataSourceImpl()
        TitleHistoryPresenter(
            this,
            NaverRepositoryImpl(naverRemoteDataSourceImpl, naverLocalDataSourceImpl)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TitleDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialog = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvSearchList.adapter = adapter
        presenter.getRecentTitleList()
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun setTitleList(list: ArrayList<QueryHistory>) {
        adapter.setTitleList(list)
    }

}