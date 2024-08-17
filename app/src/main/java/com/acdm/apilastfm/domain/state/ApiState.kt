package com.acdm.apilastfm.domain.state

import com.acdm.apilastfm.core.model.TopArtists
import com.example.pruebatecnicabolsiyo.domain.Constans

data class ApiState(
    val isLoadingApi: Boolean = false,
    val topArtists: TopArtists? = null,
    val error: String = Constans.ERROR_MESSAGE
)
