package com.example.googryandroidarchitecturestudy.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.domain.UrlResource
import com.example.googryandroidarchitecturestudy.ui.contract.BaseContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter

abstract class BaseFragment<B : ViewBinding, P : BasePresenter> : Fragment(), BaseContract.View {
    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected var progressBar: ProgressBar? = null

    protected abstract val presenter: P

    private val inputMethodManager by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    protected abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            0
        )
    }

    override fun showUrlResource(item: UrlResource) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
    }

    override fun showInvalidUrl() {
        toast(getString(R.string.invalid_url))
    }

    override fun showQueryEmpty() {
        toast(getString(R.string.no_keyword))
    }

    override fun showNoSearchResult() {
        toast(getString(R.string.no_results))
    }

    override fun showSearchFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }
}