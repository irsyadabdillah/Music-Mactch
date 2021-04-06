package com.irzstudio.musicmatch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.musicmatch.R
import com.irzstudio.musicmatch.RetrofitClient
import com.irzstudio.musicmatch.`interface`.OnListener
import com.irzstudio.musicmatch.adapter.ArtistAdapter
import com.irzstudio.musicmatch.dataartist.ArtistListResponse
import com.irzstudio.musicmatch.dataartist.ArtistResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_listofartist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArtistAdapter
    private val list = ArrayList<ArtistListResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView(){
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_view.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                requestArtistQuery(newText)
                return false
            }

        })
    }

    private fun requestArtistQuery(newText: String?){
        RetrofitClient.instance.getArtist(newText.orEmpty(), 5.toString(), "fb27cdfa3c1941cce60f4df031296d10")
            .enqueue(object : Callback<ArtistResponse> {
                override fun onResponse(
                    call: Call<ArtistResponse>,
                    response: Response<ArtistResponse>) {
                    onDataLoad(response)
                }

                override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                    t.printStackTrace()
                    tv_error.text = "Error"
                }
            })
    }

    private fun onDataLoad(response: Response<ArtistResponse>){
        list.clear()
        list.addAll(response.body()!!.message.body.artist_list)

        adapter = ArtistAdapter(list)
        rv_artist.setHasFixedSize(true)
        rv_artist.adapter = adapter
        rv_artist.layoutManager = LinearLayoutManager(this@MainActivity )

        adapter.onClickListener = object : OnListener {
            override fun onClick(artistListResponse: ArtistListResponse) {
                navigationToSong(artistListResponse)
            }
        }
    }

    private fun navigationToSong(artistListResponse : ArtistListResponse){
        val intent = Intent(applicationContext, SongActivity::class.java)
        intent.putExtra("idartist", artistListResponse.artist.artist_id )
        intent.putExtra("artistname", artistListResponse.artist.artist_name)
        startActivity(intent)
    }
}