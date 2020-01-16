package com.example.androidarchitecture.ui.image


import android.os.Bundle
import android.view.View
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.databinding.FragmentImageBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : BaseFragment<FragmentImageBinding>(R.layout.fragment_image) {


    private lateinit var imageAdapter: ImageAdapter
    private val vm by lazy { ImageViewModel(naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm

        binding.recycle.run {
            imageAdapter = ImageAdapter()
            adapter = imageAdapter
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

        vm.errorToast.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                requireContext().toast(vm.errorToast.get().toString())
            }

        })

    }
}
