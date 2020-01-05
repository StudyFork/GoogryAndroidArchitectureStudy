package wooooooak.com.studyapp.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.common.ext.toast

abstract class ItemSearchFragment<T, H : ViewDataBinding>(
    private val layoutId: Int
) : Fragment(), ItemContract.View<T> {

    protected lateinit var binding: H

    protected var userInputTitle: String = ""

    abstract val adapter: ListAdapter<T, *>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItemsByTitle(userInputTitle, true)
    }

    override fun renderItems(items: List<T>) {
        adapter.submitList(items)
    }

    override fun renderWebView(url: String) {
        requireContext().startWebView(url)
    }

    override fun renderErrorToast(message: String?) {
        message?.let { requireContext().toast(it) }
    }

    override fun renderEmptyTitleErrorToast() {
        requireContext().toast(resources.getString(R.string.error_empty_title))
    }


    abstract fun initItemsByTitle(title: String, cached: Boolean = false)

}
