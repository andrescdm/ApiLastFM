package com.acdm.apilastfm.core.network

import com.acdm.apilastfm.core.data.ApiService
import com.acdm.apilastfm.core.model.LastFMArtist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getApi(): LastFMArtist{
        return withContext(Dispatchers.IO){
            val response = apiService.getArtistTopFM()
            response
        }
    }

}