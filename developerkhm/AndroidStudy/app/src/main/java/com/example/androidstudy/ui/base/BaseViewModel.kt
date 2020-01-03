package com.example.androidstudy.ui.base

import androidx.databinding.ObservableField
import com.example.androidstudy.model.data.Item
import java.util.*

class BaseViewModel {
    var query : ObservableField<String> = ObservableField()
    var searchResult : ObservableField<ArrayList<Item>> = ObservableField()
}