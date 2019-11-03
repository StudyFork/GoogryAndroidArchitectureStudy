package com.buddman1208.architecturestudy.models

import com.google.gson.JsonObject

data class CommonResponse(
    val items: List<JsonObject> = listOf()
)