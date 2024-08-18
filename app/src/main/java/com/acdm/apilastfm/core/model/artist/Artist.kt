package com.acdm.apilastfm.core.model.artist

data class Artist(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>
)
