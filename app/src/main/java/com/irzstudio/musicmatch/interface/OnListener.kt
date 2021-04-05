package com.irzstudio.musicmatch.`interface`

import com.irzstudio.musicmatch.dataartist.ArtistListResponse

interface OnListener {
    fun onClick(artistListResponse: ArtistListResponse)
}