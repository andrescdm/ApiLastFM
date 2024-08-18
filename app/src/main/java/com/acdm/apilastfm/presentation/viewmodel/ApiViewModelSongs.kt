package com.acdm.apilastfm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acdm.apilastfm.domain.state.ApiStateSongs
import com.acdm.apilastfm.domain.usecase.api.ApiUseCaseSongs
import com.acdm.apilastfm.presentation.intent.LastFMSongIntent
import com.example.pruebatecnicabolsiyo.domain.Constans
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModelSongs @Inject constructor(
    private val apiUseCaseSongs: ApiUseCaseSongs
) : ViewModel() {
    private val _stateSongs = MutableStateFlow(ApiStateSongs())
    val stateSongs: StateFlow<ApiStateSongs> = _stateSongs

    fun processIntent(intent: LastFMSongIntent) {
        when (intent) {
            is LastFMSongIntent.FetchSongsTop -> FetchSongsTop(intent.nameArtist)
        }
    }

    private fun FetchSongsTop(nameArtist: String) {
        viewModelScope.launch {
            _stateSongs.value = _stateSongs.value.copy(isLoadingApi = true)
            try {
                val listSongs = apiUseCaseSongs.invoke(nameArtist)
                _stateSongs.value =
                    _stateSongs.value.copy(topSongs = listSongs, isLoadingApi = false)
            } catch (e: Exception) {
                _stateSongs.value = _stateSongs.value.copy(
                    error = e.message ?: Constans.UNKNOWN_ERROR,
                    isLoadingApi = false
                )
            }
        }
    }


}