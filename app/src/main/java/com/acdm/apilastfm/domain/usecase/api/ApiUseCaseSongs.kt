package com.acdm.apilastfm.domain.usecase.api

import com.acdm.apilastfm.core.ApiRepository
import com.acdm.apilastfm.core.model.songs.TopTracksResponse
import javax.inject.Inject

class ApiUseCaseSongs @Inject constructor(private val repository: ApiRepository){
    suspend operator fun invoke(nameArtist: String): TopTracksResponse{
        return repository.getApiSongs(nameArtist)
    }

}