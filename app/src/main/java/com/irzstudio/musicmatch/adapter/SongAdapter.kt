package com.irzstudio.musicmatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.musicmatch.R
import com.irzstudio.musicmatch.`interface`.OnListener
import com.irzstudio.musicmatch.datasong.TrackListResponse
import kotlinx.android.synthetic.main.item_listofsong.view.*

class SongAdapter(private val list: ArrayList<TrackListResponse>) :
    RecyclerView.Adapter<SongAdapter.SongDetailViewHolder>() {

    inner class SongDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(trackListResponse: TrackListResponse) {

            itemView.tv_trackname.text = trackListResponse.track.track_name
            itemView.tv_namealbum.text = trackListResponse.track.album_name

            if (adapterPosition % 2 == 0){
                itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.white))

            }else{
                itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.colorBackground))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongDetailViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_listofsong,parent, false)
        return SongDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

