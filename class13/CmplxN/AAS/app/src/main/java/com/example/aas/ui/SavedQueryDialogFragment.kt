package com.example.aas.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.aas.data.repository.MovieSearchRepository
import com.example.aas.data.repository.MovieSearchRepositoryImpl

class SavedQueryDialogFragment(private val historySelectionListener: HistorySelectionListener) :
    DialogFragment() {

    private val movieSearchRepository: MovieSearchRepository = MovieSearchRepositoryImpl
    private lateinit var searchHistory: List<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        searchHistory =
            movieSearchRepository.getSavedQueries(requireActivity().applicationContext).reversed()
        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("최근 5개 검색어")
                .setItems(searchHistory.toTypedArray()) { _, idx ->
                    historySelectionListener.onHistorySelection(searchHistory[idx])
                }
            builder.create()
        }
    }

    companion object {
        fun getInstance(historySelectionListener: HistorySelectionListener): SavedQueryDialogFragment {
            return SavedQueryDialogFragment(historySelectionListener)
        }
    }
}