package com.irzstudio.musicmatch.datasong

data class TrackListResponse(
    val track_id : Int,
    val track_name : String,
    val album_id : Int,
    val album_name : String,
    val artist_id : Int,
    val artist_name : String
)
