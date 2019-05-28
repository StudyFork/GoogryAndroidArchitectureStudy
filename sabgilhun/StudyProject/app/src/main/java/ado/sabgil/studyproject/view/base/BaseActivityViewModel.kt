package ado.sabgil.studyproject.view.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivityViewModel<B : ViewDataBinding>(
    private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    protected val viewModelContainer = mutableListOf<BaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onDestroy() {
        viewModelContainer.map { it.onDestroy() }
        super.onDestroy()
    }

    fun showToastMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}