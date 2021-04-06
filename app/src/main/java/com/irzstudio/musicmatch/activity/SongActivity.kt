package com.irzstudio.musicmatch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.musicmatch.R
import com.irzstudio.musicmatch.RetrofitClient
import com.irzstudio.musicmatch.adapter.SongAdapter
import com.irzstudio.musicmatch.datasong.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_error
import kotlinx.android.synthetic.main.activity_song.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongActivity : AppCompatActivity() {

    private lateinit var adapter: SongAdapter
    private val list = ArrayList<TrackListResponse>()
    private var idArtist: Int = 0
    private var nameArtist: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        loadDataSong()
        actionBar()
        requestSongQuery()

    }
    private fun requestSongQuery(){
        RetrofitClient.instance.getSong(idArtist , 100, "fb27cdfa3c1941cce60f4df031296d10")
            .enqueue(object : Callback<SongResponse> {

                override fun onResponse(
                    call: Call<SongResponse>,
                    response: Response<SongResponse>) {
                    response.body()?.let { list.addAll(it.message.body.track_list) }

                    rv_song.setHasFixedSize(true)
                    rv_song.adapter = SongAdapter(list)
                    rv_song.layoutManager = LinearLayoutManager(this@SongActivity )
                }

                override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                    t.printStackTrace()
                    tv_error.text = "Error"
                }
            })
    }

    private fun actionBar(){
        setSupportActionBar(tb_nameartist)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = nameArtist
    }

    private fun loadDataSong(){
        idArtist = intent.getIntExtra("idartist", 1)
        nameArtist = intent.getStringExtra("artistname") ?: ""
    }


}