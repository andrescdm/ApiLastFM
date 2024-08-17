package com.acdm.apilastfm.core.data

import com.acdm.apilastfm.core.model.TopArtists
import com.example.pruebatecnicabolsiyo.domain.Constans
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constans.TOP10_URL)
    suspend fun getArtistTopFM(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("country") country: String
    ): TopArtists

}