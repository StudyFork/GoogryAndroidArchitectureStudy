package com.jay.repository.model

data class Kin(
    val description: String,
    val link: String,
    val title: String
)

data class KinRepo(
    val keyword: String,
    val kins: List<Kin>
)