package com.bruno.esus.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SusCountResponse(
    @JsonProperty("count")
    val count: Long
)