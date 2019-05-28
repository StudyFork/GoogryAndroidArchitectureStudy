package ado.sabgil.studyproject.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragmentViewModel<B : ViewDataBinding>(
    private val layoutId: Int
) : Fragment() {

    protected lateinit var binding: B
        private set

    protected var viewModelContainer = mutableListOf<BaseViewModel>()
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        viewModelContainer.map { it.onDestroy() }
        super.onDestroyView()
    }

    fun showToastMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}