package com.cnm.homework.data.source.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cnm.homework.data.model.NaverResponse

@Entity(tableName = "local")
data class LocalEntity(
    @PrimaryKey(autoGenerate = true) var repo: NaverResponse.Item

)