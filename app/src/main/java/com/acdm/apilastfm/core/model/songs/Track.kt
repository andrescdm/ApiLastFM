package com.acdm.apilastfm.core.model.songs


import com.google.gson.annotations.SerializedName

data class Track(
    val name: String,
    val playcount: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val artist: Artist,
    val image: List<Image>,
    @SerializedName("@attr") val attr: Attr
)
