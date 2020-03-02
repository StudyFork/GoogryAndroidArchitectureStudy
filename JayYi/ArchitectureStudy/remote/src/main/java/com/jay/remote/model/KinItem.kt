package com.jay.remote.model

data class KinItem(
    val description: String,
    val link: String,
    val title: String
)

data class ResponseKin(
    val kins: List<KinItem>
)