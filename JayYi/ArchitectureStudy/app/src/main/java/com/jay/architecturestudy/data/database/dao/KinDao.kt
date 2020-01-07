package com.jay.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.KinEntity
import io.reactivex.Single

@Dao
interface KinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(kins: List<KinEntity>)

    @Query("SELECT * from kin")
    fun getAll(): Single<List<KinEntity>>

    @Query("DELETE from kin")
    fun clearAll()
}