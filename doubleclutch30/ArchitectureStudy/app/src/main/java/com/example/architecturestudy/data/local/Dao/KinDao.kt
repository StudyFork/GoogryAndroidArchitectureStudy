package com.example.architecturestudy.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.example.architecturestudy.data.model.BlogItem

@Dao
interface KinDao {
    @Query("SELECT * FROM kin")
    fun getAll(): List<KinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserAll(blogItem: List<KinEntity>)

    @Query("DELETE FROM kin")
    fun deleteAll()
}