package com.example.naversearchapistudy

data class KinData (val items : List<KinItems>)

data class KinItems(

    val title : String,
    val contents : String,
    val link : String )
