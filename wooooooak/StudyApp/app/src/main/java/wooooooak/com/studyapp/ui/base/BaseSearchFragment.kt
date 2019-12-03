package wooooooak.com.studyapp.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_image.view.*

abstract class BaseSearchFragment(
    private val layoutId: Int
) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var inputTextView: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.run {
            recyclerView = list_view
            setListViewAdapter(recyclerView)
            inputTextView = edit_text
            searchButton = search_button
            searchButton.setOnClickListener {
                fetchSearchedList()
            }
        }
    }

    abstract fun setListViewAdapter(listView: RecyclerView)

    protected open fun fetchSearchedList() {
        val inputText = inputTextView.text.toString()
        if (inputText.isNotEmpty()) {
            (recyclerView.adapter as? Searchable)?.searchByTitle(inputText)
        }
    }

}
