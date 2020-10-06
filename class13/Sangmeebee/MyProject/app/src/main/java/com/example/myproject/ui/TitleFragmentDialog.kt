package com.example.myproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.myproject.R
import com.example.myproject.databinding.DialogFragmentTitleBinding
import com.example.myproject.viewmodel.MainViewModel
import com.example.myproject.viewmodel.TitleViewModel

class TitleFragmentDialog : DialogFragment() {

    private lateinit var binding: DialogFragmentTitleBinding
    private val vm = TitleViewModel()
    var mainViewModel: MainViewModel? = null

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

        val adapter = TitleAdapter()
        adapter.mainViewModel = this.mainViewModel
        adapter.setTitleList(vm.titleList.get() as ArrayList<String>)
        binding.rvSearchList.adapter = adapter
    }

}
