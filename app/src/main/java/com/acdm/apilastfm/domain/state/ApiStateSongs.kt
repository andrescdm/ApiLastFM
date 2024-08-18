package com.acdm.apilastfm.domain.state

import com.acdm.apilastfm.core.model.songs.TopTracksResponse
import com.example.pruebatecnicabolsiyo.domain.Constans

data class ApiStateSongs(
    val isLoadingApi: Boolean = false,
    val topSongs: TopTracksResponse? = null,
    val error: String = Constans.ERROR_MESSAGE
)
