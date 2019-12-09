package com.example.seonoh.seonohapp

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.seonoh.seonohapp.model.BaseViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity() {

    private lateinit var toast: Toast
    protected lateinit var binding: B
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        toast = Toast.makeText(this, resources.getString(R.string.back_text), Toast.LENGTH_LONG)
    }

//    @Inject
//    lateinit var viewModelFactory : ViewModelProvider.Factory

    private fun showToast() {
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    override fun onBackPressed() {
        showToast()
    }
}