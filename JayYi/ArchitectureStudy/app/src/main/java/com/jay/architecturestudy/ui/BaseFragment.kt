package com.jay.architecturestudy.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jay.architecturestudy.R
import kotlinx.android.synthetic.main.view_search.*

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_btn.setOnClickListener {
            val keyword = search_editor.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(activity, getString(R.string.warn_input_keyword), Toast.LENGTH_SHORT).show()
            } else {
                hideKeyboard()
                search(keyword)
            }
        }

    }

    fun hideKeyboard() {
        val activity = activity ?: return
        val currentFocus = activity.currentFocus ?: return

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    open fun search(keyword: String) {
    }
}