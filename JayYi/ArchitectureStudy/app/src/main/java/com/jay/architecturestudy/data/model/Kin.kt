package com.jay.architecturestudy.data.model

data class Kin(
    val description: String,
    val link: String,
    val title: String
)

data class ResponseKin(
    val kins: List<Kin>
)

data class KinRepo(
    val keyword: String,
    val kins: List<Kin>
)