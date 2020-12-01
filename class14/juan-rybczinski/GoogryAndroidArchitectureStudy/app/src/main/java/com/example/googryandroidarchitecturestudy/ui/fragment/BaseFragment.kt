package com.example.googryandroidarchitecturestudy.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.googryandroidarchitecturestudy.BR
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.viewmodel.BaseViewModel
import com.example.googryandroidarchitecturestudy.ui.viewmodel.BaseViewModel.Companion.INVALID_URL

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int
) : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

    abstract val viewModel: VM

    private val inputMethodManager by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.vm, viewModel)

        setupBaseUi()
    }

    private fun setupBaseUi() {
        viewModel.apply {
            hideKeyboardEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    hideKeyboard()
                }
            })

            selectedUrl.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (!selectedUrl.equals(INVALID_URL)) {
                        selectedUrl.get()?.let { showUrlResource(it) }
                    } else {
                        showInvalidUrl()
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            0
        )
    }

    private fun showUrlResource(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun showInvalidUrl() {
        toast(getString(R.string.invalid_url))
    }
}