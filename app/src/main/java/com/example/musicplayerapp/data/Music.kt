package com.example.musicplayerapp.data

data class Music(
    val id: Int?,
    val songname: String?,
    val artistname: String?,
    var albumCover: ByteArray?,
    val songPath: String?,
    val songDuration: Int?,
    val gaane: ArrayList<String>,
    val musicname: ArrayList<String>,
    val singername: ArrayList<String>,
    val songpath: ArrayList<String>
)
