package com.acdm.apilastfm.core

import com.acdm.apilastfm.core.data.ApiService
import com.acdm.apilastfm.core.model.artist.TopArtists
import com.acdm.apilastfm.core.model.songs.TopTracksResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getApi(): TopArtists {
        return withContext(Dispatchers.IO) {
            val response =
                apiService.getArtistTopFM(
                    "cf2894b9c73a323e24f5c6a9aab1eb85",
                    10,
                    "colombia"
                )
            response
        }
    }

    suspend fun getApiSongs(nameArtist: String): TopTracksResponse {
        return withContext(Dispatchers.IO) {
            val response =
                apiService.getSongsTopFM(
                    "cf2894b9c73a323e24f5c6a9aab1eb85",
                    5,
                    nameArtist
                )
            response
        }
    }
}