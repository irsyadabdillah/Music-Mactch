package com.irzstudio.musicmatch

import com.irzstudio.musicmatch.dataartist.ArtistDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("artist.search")
    fun getArtist(
        @Query("q_artist") q_artist: String,
        @Query("page_size") page_size: Int,
        @Query("apikey") apikey: String
    ): Call<ArtistDetailResponse>

}