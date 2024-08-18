package com.acdm.apilastfm.core.model.artist

import com.google.gson.annotations.SerializedName

data class TopArtistsInfo(
    val artist: List<Artist>,
    @SerializedName("@attr") val attr: Attr
)
