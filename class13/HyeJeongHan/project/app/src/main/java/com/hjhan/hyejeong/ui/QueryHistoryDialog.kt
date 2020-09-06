package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.R


class QueryHistoryDialog : DialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var emptyText: TextView
    private lateinit var queryHistoryAdapter: QueryHistoryAdapter

    private var list: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_query_history, container)

        val bundle = arguments
        if (bundle != null) {
            list = bundle.getStringArrayList("list") as List<String>
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.query_recycler_view)
        button = view.findViewById<Button>(R.id.close_button)
        emptyText = view.findViewById<Button>(R.id.empty_list_text)

        button.setOnClickListener {
            dismiss()
        }

        if (list.isNotEmpty()) {
            //목록 있을떄
            recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE

            queryHistoryAdapter = QueryHistoryAdapter()
            recyclerView.adapter = queryHistoryAdapter

            queryHistoryAdapter.setMovieList(list)

        } else {
            //목록 없을때
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        }


        return view
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}