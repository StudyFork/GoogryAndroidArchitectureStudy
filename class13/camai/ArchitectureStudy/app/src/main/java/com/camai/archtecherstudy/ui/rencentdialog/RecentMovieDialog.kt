package com.camai.archtecherstudy.ui.rencentdialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import kotlinx.android.synthetic.main.recent_movie_list_popup.*

class RecentMovieDialog(var keywork: (String) -> Unit) : DialogFragment(),
    RecentMovieContract.View {

    private lateinit var recentMovieAdapter: RecentMovieAdapter

    private val recentPresenter: RecentMovieContract.Presenter by lazy {
        RecentMoviePresenter(this, MovieRepositoryImpl)
    }

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterAndRecyclerViewInit()

        //  Popup close
        popup_close.setOnClickListener(View.OnClickListener {
            recentPresenter.closeDialog()
        })

    }


    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {
        //   init Adapter
        recentMovieAdapter =
            RecentMovieAdapter {
                Log.d(TAG, it)
                //  recycler View item click movie name to Activity
                keywork.invoke(it)

                recentPresenter.closeDialog()
            }

        recentMovieAdapter.notifyDataSetChanged()

        //  recycler view init and adapter connect
        recycler_recent_view.run {
            adapter = recentMovieAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        //  get Recent Data
        recentPresenter.setRecentData()
    }


    override fun setDataInsertToAdapter(data: List<RecentSearchName>) {
        recentMovieAdapter.setClearAndAddList(data)
    }

    override fun showEmptyFieldText() {
        Toast.makeText(requireContext(), "최근 검색된 항목이 없습니다.", Toast.LENGTH_LONG).show()
    }

    override fun closeDialog() {
        dismiss()
    }
}