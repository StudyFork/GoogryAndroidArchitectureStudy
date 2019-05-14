package ado.sabgil.studyproject.view.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding, P : BaseContract.Presenter> constructor(
    private val layoutId: Int
) : AppCompatActivity(), BaseContract.View {

    protected lateinit var binding: B
        private set

    protected lateinit var presenter: P
        private set

    protected val disposables = CompositeDisposable()

    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        presenter = createPresenter()
    }

    override fun onDestroy() {
        disposables.clear()
        presenter.unSubscribe()
        super.onDestroy()
    }

    override fun showToastMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}