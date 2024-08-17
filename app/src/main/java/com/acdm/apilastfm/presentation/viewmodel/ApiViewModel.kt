package com.acdm.apilastfm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acdm.apilastfm.domain.state.ApiState
import com.acdm.apilastfm.domain.usecase.api.ApiUseCase
import com.acdm.apilastfm.presentation.intent.LastFMArtistIntent
import com.example.pruebatecnicabolsiyo.domain.Constans
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiUseCase: ApiUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ApiState())
    val state: StateFlow<ApiState> = _state

    fun processIntent(intent: LastFMArtistIntent) {
        when (intent) {
            is LastFMArtistIntent.FetchArtistTop -> FetchArtistTop()
        }
    }

    private fun FetchArtistTop() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingApi = true)
            try {
                val listArtist = apiUseCase.invoke()
                _state.value = _state.value.copy(topArtists = listArtist, isLoadingApi = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: Constans.UNKNOWN_ERROR,
                    isLoadingApi = false
                )
            }
        }
    }
}