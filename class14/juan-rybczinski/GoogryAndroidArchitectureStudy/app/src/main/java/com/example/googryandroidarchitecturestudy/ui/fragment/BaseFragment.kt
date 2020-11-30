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
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.domain.UrlResource
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.viewmodel.BaseViewModel

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int
) : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            0
        )
    }

    fun showUrlResource(item: UrlResource) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
    }

    fun showInvalidUrl() {
        toast(getString(R.string.invalid_url))
    }
}