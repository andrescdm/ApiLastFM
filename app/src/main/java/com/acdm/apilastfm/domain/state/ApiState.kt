package com.acdm.apilastfm.domain.state

import com.acdm.apilastfm.core.model.LastFMArtist
import com.example.pruebatecnicabolsiyo.domain.Constans

data class ApiState(
    val isLoadingApi: Boolean = false,
    val lastFMArtist: LastFMArtist? = null,
    val error: String = Constans.ERROR_MESSAGE
)
