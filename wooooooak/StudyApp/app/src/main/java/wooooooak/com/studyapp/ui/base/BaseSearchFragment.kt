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

abstract class BaseSearchFragment : Fragment() {

    abstract val layoutId: Int
    private lateinit var fragmentView: View
    private lateinit var listView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var inputTextView: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(layoutId, container, false)
        listView = fragmentView.list_view
        setListViewAdapter(listView)
        inputTextView = fragmentView.edit_text
        searchButton = fragmentView.search_button
        searchButton.setOnClickListener {
            fetchSearchedList()
        }
        return fragmentView
    }

    abstract fun setListViewAdapter(listView: RecyclerView)

    protected open fun fetchSearchedList() {
        val adapter = listView.adapter
        val inputText = inputTextView.text.toString()
        adapter?.let {
            if (it is Searchable && inputText.isNotEmpty()) {
                it.searchByTitle(inputText)
            }
        }
    }


}
