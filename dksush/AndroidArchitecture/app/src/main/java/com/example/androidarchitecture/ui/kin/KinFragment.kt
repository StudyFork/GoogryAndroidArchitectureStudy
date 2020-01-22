package com.example.androidarchitecture.ui.kin


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.databinding.FragmentKinBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class KinFragment : BaseFragment<FragmentKinBinding>(R.layout.fragment_kin) {
    private lateinit var kinAdapter: KinAdapter
    private val vm by lazy { KinViewModel(naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.vm = vm
        binding.lifecycleOwner = this

        binding.recycle.run {
            kinAdapter = KinAdapter()
            adapter = kinAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            vm.requestSearchHist()
        }

        observeListener()
    }


    private fun observeListener() {
        vm.blankInputText.observe(this, Observer {
            requireContext().toast(getString(R.string.black_input_text))
        })


        vm.errorToast.observe(this, Observer {
            requireContext().toast(it.toString())
        })
    }


}
