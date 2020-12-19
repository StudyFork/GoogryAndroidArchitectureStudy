package com.example.googryandroidarchitecturestudy.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import com.example.googryandroidarchitecturestudy.BR
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

    private val clazz = ((javaClass.genericSuperclass as ParameterizedType?)
        ?.actualTypeArguments
        ?.get(1) as Class<VM>).kotlin

    protected val viewModel by ViewModelLazy(
        clazz,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )

    private val inputMethodManager by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, layoutResId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        setupBaseUi()
    }

    private fun setupBaseUi() {
        with(viewModel) {
            hideKeyboardEvent.observe(viewLifecycleOwner) {
                hideKeyboard()
            }

            selectedUrl.observe(viewLifecycleOwner) {
                showUrlResource(it)
            }

            showInvalidUrlEvent.observe(viewLifecycleOwner) {
                showInvalidUrl()
            }
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