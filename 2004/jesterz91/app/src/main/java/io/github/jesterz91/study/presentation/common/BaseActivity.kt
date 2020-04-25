package io.github.jesterz91.study.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.github.jesterz91.study.presentation.extension.toast
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<P : BaseContract.Presenter, B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity(), BaseContract.View {

    abstract val presenter: P

    protected val binding: B by lazy { inflate.invoke(layoutInflater) }

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        disposables.clear()
        presenter.clearDisposables()
        super.onDestroy()
    }

    override fun showToast(message: String) = toast(message)

    override fun hideSoftKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}