package com.cnm.homework.data.source.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cnm.homework.data.model.NaverResponse

@Entity(tableName = "local")
data class LocalEntity(
    @PrimaryKey
    var id: Long,
    @TypeConverters(LocalConverters::class)
    var repo: List<NaverResponse.Item>

)