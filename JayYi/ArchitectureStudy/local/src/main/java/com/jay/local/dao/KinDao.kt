package com.jay.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.local.model.KinEntity
import io.reactivex.Single

@Dao
internal interface KinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(kins: List<KinEntity>)

    @Query("SELECT * from kin")
    fun getAll(): Single<List<KinEntity>>

    @Query("DELETE from kin")
    fun clearAll()
}