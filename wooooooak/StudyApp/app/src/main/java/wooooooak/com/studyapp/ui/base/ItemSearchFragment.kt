package wooooooak.com.studyapp.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_image.view.*
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.common.ext.toast

abstract class ItemSearchFragment<T>(
    private val layoutId: Int
) : Fragment(), ItemContract.View<T> {

    abstract val adapter: ListAdapter<T, *>
    private lateinit var searchButton: Button
    private lateinit var inputTextView: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.run {
            list_view.adapter = adapter
            inputTextView = edit_text
            searchButton = search_button
            searchButton.setOnClickListener {
                lifecycleScope.launch {
                    initItemsByTitle(inputTextView.text.toString())
                }
            }
        }
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

    abstract fun initItemsByTitle(title: String)

}
