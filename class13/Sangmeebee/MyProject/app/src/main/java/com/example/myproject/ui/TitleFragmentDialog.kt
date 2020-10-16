package com.example.myproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.myproject.R
import com.example.myproject.databinding.DialogFragmentTitleBinding
import com.example.myproject.viewmodel.MainViewModel

class TitleFragmentDialog : DialogFragment() {

    private lateinit var binding: DialogFragmentTitleBinding
    private val vm by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_title, container, false)?.apply {
            binding = DataBindingUtil.bind(this)!!
            binding.dialog = this@TitleFragmentDialog
            vm.callTitleList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TitleAdapter {
            vm.query.value = it
            dismiss()
        }
        vm.titleList.value?.let { adapter.setTitleList(it) }
        binding.rvSearchList.adapter = adapter
    }

}
