package com.example.kangraemin.model.local.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "auto_login") val autoLogin: Boolean,
    @ColumnInfo(name = "user_name") val userName: String
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}