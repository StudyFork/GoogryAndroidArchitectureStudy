package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hjhan.hyejeong.databinding.DialogQueryHistoryBinding


class QueryHistoryDialog : DialogFragment() {

    private lateinit var binding: DialogQueryHistoryBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setInflateLayout(inflater, container)

        return binding.root
    }

    private fun setInflateLayout(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DialogQueryHistoryBinding.inflate(inflater, container, false)
        binding.dialog = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getRecentQueryList()
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    fun onCloseButtonClicked() {
        dismiss()
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}