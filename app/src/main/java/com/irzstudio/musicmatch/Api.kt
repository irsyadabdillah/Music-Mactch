package com.irzstudio.musicmatch

import com.irzstudio.musicmatch.dataartist.ArtistResponse
import com.irzstudio.musicmatch.datasong.MessageSongResponse
import com.irzstudio.musicmatch.datasong.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("artist.search")
    fun getArtist(
        @Query("q_artist") q_artist: String,
        @Query("page_size") page_size: String?,
        @Query("apikey") apikey: String
    ): Call<ArtistResponse>

    @GET ("track.search")
    fun getSong(
        @Query("f_artist_id") f_artist_id: Int,
        @Query("page_size") page_size: Int,
        @Query("apikey") apikey: String
    ):Call<SongResponse>

}