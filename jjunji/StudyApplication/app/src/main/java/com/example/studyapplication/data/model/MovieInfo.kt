package com.example.studyapplication.data.model

import io.realm.RealmObject

open class MovieInfo(
        var title: String = "",
        var image: String = "",
        var director: String = "",
        var actor: String = ""
) : RealmObject()