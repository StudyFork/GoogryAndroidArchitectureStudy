package com.example.androidarchitecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import com.example.androidarchitecturestudy.databinding.TitleDialogFragmentBinding

class TitleFragmentDialog : DialogFragment(), TitleHistoryContract.View {

    private lateinit var binding: TitleDialogFragmentBinding
    private lateinit var adapter: TitleAdapter

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
        binding.dialog = this
        initRecyclerView()
        return binding.root
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

    override fun setTitleList(list: ArrayList<String>) {
        adapter.setTitleList(list)
    }

}