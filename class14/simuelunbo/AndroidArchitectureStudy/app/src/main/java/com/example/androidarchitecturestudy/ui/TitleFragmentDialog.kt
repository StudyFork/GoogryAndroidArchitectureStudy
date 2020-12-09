package com.example.androidarchitecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import com.example.androidarchitecturestudy.databinding.TitleDialogFragmentBinding
import com.example.androidarchitecturestudy.ui.main.MainViewModel

class TitleFragmentDialog : DialogFragment() {

    private lateinit var binding: TitleDialogFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter by lazy {
        TitleAdapter()
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
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()

        viewModel.getRecentTitleList()
    }

    private fun initRecyclerView() {
        binding.rvSearchList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}