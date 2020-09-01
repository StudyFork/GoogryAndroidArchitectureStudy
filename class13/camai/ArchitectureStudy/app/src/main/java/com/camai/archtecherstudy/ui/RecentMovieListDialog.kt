package com.camai.archtecherstudy.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.model.RecentMovieNameViewModel
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.source.local.room.RecentSearchNameList
import com.camai.archtecherstudy.ui.adapter.RecentMovieAdapter
import kotlinx.android.synthetic.main.recent_movie_list_popup.*

class RecentMovieListDialog : DialogFragment() {

    private lateinit var recentMovieAdapter: RecentMovieAdapter
    private var recentnameList = listOf<RecentSearchNameList>()
    private lateinit var viewModel: RecentMovieNameViewModel

    companion object {
        const val TAG = "MovieSearchDialog"
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recent_movie_list_popup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            MovieRepositoryImpl.getRecentSearchList(namelist = { namelist ->
                recentnameList = namelist
                if (namelist.isEmpty()) {
                    showEmptyFieldList(it)
                } else {
                    setAdapterAndRecyclerViewInit()
                }

            }, context = it)
        }

        //  Popup close
        popup_close.setOnClickListener(View.OnClickListener {
            dismiss()
        })
    }

    private fun showEmptyFieldList(context: Context) {
        Toast.makeText(context, "최근 검색된 항목이 없습니다.", Toast.LENGTH_LONG).show()
    }

    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {

        recentMovieAdapter =
            RecentMovieAdapter(recentnameList) { movieName ->
                Log.d(TAG, movieName)
                //  recycler View item click movie name to Activity
                viewModel.sendMovieName(movieName)
                dismiss()
            }
        recentMovieAdapter.notifyDataSetChanged()

        //  recycler view init and adapter connect
        recycler_recent_view.run {
            adapter = recentMovieAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RecentMovieNameViewModel::class.java)

    }

    override fun onDestroy() {
        MovieRepositoryImpl.dbclose()
        super.onDestroy()
    }
}