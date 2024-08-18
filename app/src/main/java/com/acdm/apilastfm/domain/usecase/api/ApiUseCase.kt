package com.acdm.apilastfm.domain.usecase.api


import com.acdm.apilastfm.core.model.artist.TopArtists
import com.acdm.apilastfm.core.ApiRepository
import javax.inject.Inject

class ApiUseCase @Inject constructor(private val repository: ApiRepository){

    suspend operator fun invoke(): TopArtists {
        return repository.getApi()
    }

}