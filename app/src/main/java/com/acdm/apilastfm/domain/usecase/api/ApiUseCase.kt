package com.acdm.apilastfm.domain.usecase.api


import com.acdm.apilastfm.core.model.LastFMArtist
import com.acdm.apilastfm.core.network.ApiRepository
import javax.inject.Inject

class ApiUseCase @Inject constructor(private val repository: ApiRepository){

    suspend operator fun invoke(): LastFMArtist {
        return repository.getApi()
    }
}