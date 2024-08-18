package com.acdm.apilastfm.presentation.intent

sealed class LastFMSongIntent{
    data class FetchSongsTop(var nameArtist: String) : LastFMSongIntent()
}
