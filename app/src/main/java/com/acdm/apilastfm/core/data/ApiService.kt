package com.acdm.apilastfm.core.data

import com.acdm.apilastfm.core.model.artist.TopArtists
import com.acdm.apilastfm.core.model.songs.TopTracksResponse
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

    @GET(Constans.SONG_URL)
    suspend fun getSongsTopFM(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("artist") artist: String
    ): TopTracksResponse

}