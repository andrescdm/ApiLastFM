package com.acdm.apilastfm.core.data

import com.acdm.apilastfm.core.model.LastFMArtist
import com.example.pruebatecnicabolsiyo.domain.Constans
import retrofit2.http.GET

interface ApiService {

    @GET()
    suspend fun getArtistTopFM(): LastFMArtist

}