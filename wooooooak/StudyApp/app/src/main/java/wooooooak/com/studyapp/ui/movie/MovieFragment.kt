package wooooooak.com.studyapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_movie.view.*
import wooooooak.com.studyapp.R

class MovieFragment : Fragment() {

    private lateinit var movieListView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var inputTextView: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        movieListView = view.list_view
        movieListView.adapter = MovieListAdapter(this.requireActivity())
        searchButton = view.search_button
        inputTextView = view.edit_text

        searchButton.setOnClickListener {
            inputTextView.text?.let { text ->
                if (text.isNotEmpty()) {
                    (movieListView.adapter as MovieListAdapter).searchByTitle(text.toString())
                }
            }
        }

        return view
    }

}
