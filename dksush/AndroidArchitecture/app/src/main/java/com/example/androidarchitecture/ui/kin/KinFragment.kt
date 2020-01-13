package com.example.androidarchitecture.ui.kin


import android.os.Bundle
import android.view.View
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
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
        vm.blankInputText.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                requireContext().toast(getString(R.string.black_input_text))
            }

        })

        vm.renderItems.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.renderItems.get()?.let {
                    kinAdapter.setData(it)
                }
            }

        })

        vm.errorToast.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                requireContext().toast(vm.errorToast.get().toString())
            }

        })

        vm.isListEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.isListEmpty = vm.isListEmpty.get()

            }

        })

    }


}
