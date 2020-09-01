package com.hong.architecturestudy.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl

class MovieListDialogFragment : DialogFragment() {

    private val movieSearchListAdapter = MovieSearchListAdapter()

    private val repositoryDataSourceImpl: RepositoryDataSource by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.dialog_fragment_movie_list, null)
        val rvSearchItem = view?.findViewById<RecyclerView>(R.id.rv_search_list)

        rvSearchItem?.adapter = movieSearchListAdapter
        rvSearchItem?.setHasFixedSize(true)

        repositoryDataSourceImpl.loadData(this, Observer {
            movieSearchListAdapter.setList(it)
        }, requireContext())

        movieSearchListAdapter.onClick = {
            (requireActivity() as MainActivity).titleListener.invoke(it)
        }

        val dialog = AlertDialog.Builder(requireActivity())
        dialog.setView(view)
        dialog.setTitle("최근 검색")

        return dialog.create()

    }

    companion object {
        fun newInstance(): MovieListDialogFragment {
            return MovieListDialogFragment()
        }
    }
}