package ado.sabgil.studyproject.view.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<B : ViewDataBinding>(
    private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    protected var progressBar: View? = null

    protected fun <VM : ViewModel> getActivityScopeViewModel(
        vmClass: Class<VM>,
        viewModelFactory: ViewModelProvider.NewInstanceFactory
    ) = ViewModelProviders.of(this, viewModelFactory).get(vmClass)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    protected fun showToastMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    protected fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    protected fun bind(block: B.() -> Unit) {
        binding.block()
    }
}