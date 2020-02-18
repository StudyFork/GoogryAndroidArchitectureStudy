package com.example.studyapplication.data.model.cache

import com.example.studyapplication.data.model.KinInfo
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CacheKin : RealmObject() {
    @PrimaryKey
    open var id : Int = 1
    open var kinList : RealmList<KinInfo> = RealmList()
}