package com.example.musicplayerapp.adapter

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.R
import com.example.musicplayerapp.data.Music
import com.example.musicplayerapp.databinding.ViewsLayoutBinding

class SongViewHolder(val binding: ViewsLayoutBinding): RecyclerView.ViewHolder(binding.root){
    interface viewholdercommunivator{
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
    lateinit var obj: viewholdercommunivator
    fun values(data: viewholdercommunivator){
        obj = data
    }

    fun bind(songsData: Music){
        if(songsData.songname!=null) {
            binding.text1.text = songsData.songname
        }
        else{
            binding.text1.text = "Come Through"
        }
        if(songsData.artistname!=null) {
            binding.textView.text = songsData.artistname
        }else{
            binding.textView.text = "Alec"
        }
        if(songsData.albumCover!=null) {
            binding.imageView.setImageBitmap(songsData.albumCover?.let {
                BitmapFactory.decodeByteArray(songsData.albumCover,
                    0,
                    it.size)
            })
        }
        else{
            binding.imageView.setImageResource(R.drawable.download)
        }
        binding.text1.setOnClickListener {
            obj.viewholdData(songsData.id,songsData.songname, songsData.artistname, songsData.albumCover, songsData.songPath, songsData.songDuration, songsData.gaane, songsData.musicname, songsData.singername, songsData.songpath)
        }
    }
}