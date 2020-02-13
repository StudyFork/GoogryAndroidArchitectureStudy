package app.ch.study.core

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, P : BaseContract.Presenter>(@LayoutRes private val resId: Int) :
    AppCompatActivity(resId), BaseContract.View {

    abstract val presenter: P
    abstract val pbLoading: FrameLayout?

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, resId)
    }

    override fun showLoading() {
        pbLoading?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoading?.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }

}