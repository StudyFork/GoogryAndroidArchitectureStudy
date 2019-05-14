package ado.sabgil.studyproject.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding, P : BaseContract.Presenter> constructor(
    private val layoutId: Int
) : Fragment(), BaseContract.View {

    protected lateinit var binding: B
        private set

    protected lateinit var presenter: P
        private set

    protected abstract fun createPresenter(): P

    protected val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        presenter = createPresenter()
        return binding.root
    }

    override fun onDestroyView() {
        disposables.clear()
        presenter.unSubscribe()
        super.onDestroyView()
    }

    override fun showToastMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}