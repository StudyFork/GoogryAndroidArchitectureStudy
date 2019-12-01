package com.egiwon.architecturestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BaseContract.Presenter>(
    @LayoutRes private val layoutResId: Int
) : Fragment(), BaseContract.View {

    abstract val presenter: P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(textResId: Int) {
        showToast(getString(textResId))
    }
}