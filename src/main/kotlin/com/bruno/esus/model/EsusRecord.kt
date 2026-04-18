package com.bruno.esus.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class EsusRecord(
    val id: String? = null,
    val municipio: String? = null,
    val estado: String? = null,
    val dataNotificacao: String? = null,
    val resultadoTeste: String? = null,
    val registroAtual: Boolean = true
)