package com.jay.architecturestudy.data.model

import com.jay.architecturestudy.data.database.entity.KinEntity

data class Kin(
    val description: String,
    val link: String,
    val title: String
)

data class KinRepo(
    val keyword: String,
    val kins: List<Kin>
)

data class ResponseKin(
    val kins: List<Kin>
)

data class KinLocalData(
    val kins: List<KinEntity>
)