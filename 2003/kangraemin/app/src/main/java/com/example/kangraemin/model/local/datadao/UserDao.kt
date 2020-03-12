package com.example.kangraemin.model.local.datadao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kangraemin.model.local.datamodel.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE idx IN (:idx)")
    fun loadAllByIds(idx: IntArray): List<User>

    @Query("SELECT * FROM user WHERE idx LIKE (:userId) LIMIT 1")
    fun findByUserId(userId: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}