package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.KinEntity
import io.reactivex.Single

interface KinDao {
    @Insert
    fun insertAll(movies: List<KinEntity>)

    @Query("SELECT * from kin")
    fun getAll(): Single<List<KinEntity>>

    @Query("DELETE from kin")
    fun clearAll()
}