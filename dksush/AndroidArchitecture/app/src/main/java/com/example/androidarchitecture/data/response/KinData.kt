package com.example.androidarchitecture.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kin")
data class KinData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String,
    val link: String,
    val title: String
)
