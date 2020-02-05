package app.ch.study.core

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseContract.Presenter>(@LayoutRes resId: Int) :
    AppCompatActivity(resId), BaseContract.View {

    abstract val presenter: P
    abstract val pbLoading: FrameLayout?

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