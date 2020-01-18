package com.cnm.homework.data.source.local

import android.util.Log
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalEntity
import io.reactivex.Single

class NaverQueryLocalDataSourceImpl(private val dao : LocalDao) : NaverQueryLocalDataSource {
    
    override fun saveCacheMovie(localEntity: LocalEntity){
        Log.e("sava","들어옴")
        dao.insertLocal(localEntity)
    }

    override fun loadLocal(): List<LocalEntity> {
        return dao.loadLocal()
    }

}