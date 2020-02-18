package com.example.studyapplication.data.model

import io.realm.RealmObject

open class KinInfo(
    var title: String = "",
    var link: String = "",
    var description: String = ""
) : RealmObject()