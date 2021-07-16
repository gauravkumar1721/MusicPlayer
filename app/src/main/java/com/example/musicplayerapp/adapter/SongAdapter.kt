package com.example.musicplayerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.data.Music
import com.example.musicplayerapp.databinding.ViewsLayoutBinding
import java.time.Duration

class SongAdapter(val songData: ArrayList<Music>): RecyclerView.Adapter<SongViewHolder>(), SongViewHolder.viewholdercommunivator {
    interface SongAdapterInterface{
        fun viewholdData(
            id: Int?,
            songname: String?,
            artistname: String?,
            albumimg: ByteArray?,
            songPath: String?,
            songDuration: Int?,
            gaane: ArrayList<String>,
            musicname: ArrayList<String>,
            singername: ArrayList<String>,
            songpath: ArrayList<String>
        )
    }
    lateinit var obj: SongAdapterInterface
    fun setData(data: SongAdapterInterface){
        obj = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ViewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songsdt = songData[position]
        holder.values(this)
        holder.bind(songsdt)
    }

    override fun getItemCount(): Int {
        return songData.size
    }

    override fun viewholdData(id: Int?, songname: String?, artistname: String?, albumimg: ByteArray?, songPath: String?, songDuration: Int?, gaane: ArrayList<String>, musicname: ArrayList<String>, singername: ArrayList<String>, songpath: ArrayList<String>) {
        obj.viewholdData(id,songname, artistname, albumimg, songPath, songDuration, gaane, musicname, singername, songpath)
    }
}