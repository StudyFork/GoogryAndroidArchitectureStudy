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
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl


class QueryHistoryDialog : DialogFragment(), QueryHistoryContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var emptyText: TextView
    private lateinit var queryHistoryAdapter: QueryHistoryAdapter

    private val presenter: QueryHistoryContract.Presenter by lazy {
        val remoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val localDataSourceImpl = NaverLocalDataSourceImpl()
        QueryHistoryPresenter(
            this@QueryHistoryDialog,
            NaverRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_query_history, container)

        recyclerView = view.findViewById<RecyclerView>(R.id.query_recycler_view)
        button = view.findViewById<Button>(R.id.close_button)
        emptyText = view.findViewById<Button>(R.id.empty_list_text)

        button.setOnClickListener {
            dismiss()
        }

        presenter.getRecentQueryList()

        return view
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun emptyQueryList() {
        //목록 없을때
        recyclerView.visibility = View.GONE
        emptyText.visibility = View.VISIBLE
    }

    override fun setRecentQueryList(list: List<String>) {
        //목록 있을떄
        recyclerView.visibility = View.VISIBLE
        emptyText.visibility = View.GONE

        queryHistoryAdapter = QueryHistoryAdapter()
        recyclerView.adapter = queryHistoryAdapter

        queryHistoryAdapter.setMovieList(list)
    }

    companion object {
        const val HISTORY_DIALOG_TAG = "HISTORY_DIALOG_TAG"
    }
}