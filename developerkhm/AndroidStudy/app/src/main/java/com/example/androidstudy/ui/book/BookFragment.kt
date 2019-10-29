package com.example.androidstudy.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidstudy.R
import com.example.androidstudy.R.layout

class BookFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View =
            inflater.inflate(layout.fragment_news, container, false)
        val textView: TextView =
            root.findViewById(R.id.text_dashboard)
        textView.text = "book"
        return root
    }
}