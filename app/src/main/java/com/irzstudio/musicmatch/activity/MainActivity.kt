package com.irzstudio.musicmatch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.musicmatch.R
import com.irzstudio.musicmatch.RetrofitClient
import com.irzstudio.musicmatch.adapter.ArtistAdapter
import com.irzstudio.musicmatch.dataartist.ArtistDetailResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_listofartist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArtistAdapter
    private val list = ArrayList<ArtistDetailResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.instance.getArtist("justin", 5, "fb27cdfa3c1941cce60f4df031296d10")
            .enqueue(object : Callback<ArtistDetailResponse> {


                override fun onResponse(
                    call: Call<ArtistDetailResponse>,
                    response: Response<ArtistDetailResponse>) {
                    response.body()?.let { list.addAll(listOf(it)) }

                    adapter = ArtistAdapter(list)
                    rv_artist.setHasFixedSize(true)
                    rv_artist.adapter = adapter
                    rv_artist.layoutManager = LinearLayoutManager(this@MainActivity )
                }

                override fun onFailure(call: Call<ArtistDetailResponse>, t: Throwable) {
                    t.printStackTrace()
                    tv_error.text = "Error"
                }

            })


    }
}