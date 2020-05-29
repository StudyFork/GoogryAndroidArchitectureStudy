package io.github.jesterz91.study.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VM : BaseViewModel, B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    abstract val viewModel: VM

    protected val binding: B by lazy { inflate.invoke(layoutInflater) }

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    fun hideSoftKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}