package com.example.kangraemin.model.local.datadao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AuthDao {
    @Query("SELECT * FROM Auth LIMIT 1")
    fun getAuth(): Single<Auth>

    @Insert
    fun insertAuth(auth: Auth): Completable

    @Query("DELETE from Auth")
    fun deleteAuth(): Completable
}