package com.example.androidstudy.ui.base

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.ObservableField
import com.example.androidstudy.model.data.Item
import java.util.*

class BaseViewModel {
    var query : ObservableField<String> = ObservableField()
    var searchResult : ObservableField<ArrayList<Item>> = ObservableField()



    fun onEditorActionClicked(view: TextView, actionId: Int?, event: KeyEvent?) : Boolean {
        when(actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {

            }
        }
        return false
    }

}