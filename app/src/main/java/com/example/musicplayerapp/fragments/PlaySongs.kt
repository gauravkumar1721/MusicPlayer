package com.example.musicplayerapp.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.musicplayerapp.R
import com.example.musicplayerapp.databinding.FragmentPlaySongsBinding
import com.example.musicplayerapp.service.playmusic
import java.lang.Exception


class PlaySongs : Fragment() {
lateinit var binding: FragmentPlaySongsBinding
var mcontext = context
val musiclist = "music"
    var allmusiclist: ArrayList<String?> = ArrayList()
    var datafour: String? = null
    val metaRetriver: MediaMetadataRetriever = MediaMetadataRetriever()
    lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_songs, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val data = arguments?.getString("my songName")
        if (data != null) {
            Log.d("my text data", data)
        }
        val datatwo = arguments?.getString("my artistName")
        datatwo?.let { Log.d("my text data", it) }
        val datathree = arguments?.getByteArray("my img")
        datafour = arguments?.getString("song path")
        val datafive = arguments?.getInt("song duration")
        var size = arguments?.getInt("song size")
         var id = arguments?.getInt("id")
        val gaane = arguments?.getStringArrayList("gaane")
        val musicname = arguments?.getStringArrayList("musicname")
        val artistname = arguments?.getStringArrayList("artistname")
        val albumcover = arguments?.getStringArrayList("albumcover")
        Log.d("id", "id $id")
        Log.d("size", "size $size")
        binding.songname.text = data
        binding.artn1.text = datatwo
        if(datathree!=null) {
            binding.imgalbum.setImageBitmap(datathree.let {
                datathree?.size?.let { it1 ->
                    BitmapFactory.decodeByteArray(datathree,
                        0,
                        it1)
                }
            })
        }
        else{
            binding.imgalbum.setImageResource(R.drawable.download)
        }
        //binding.imgalbum.setImageBitmap(datathree)
        allmusiclist.add(datafour)
        Log.d("my song path", "$datafour")

        binding.play.setOnClickListener {
            onClick()
        }
        binding.backward.setOnClickListener {
            Log.d("backward button", "clicked")
            var y: Int? = id
            if(player!=null) {
                binding.play.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                if (y == null) {
                    Log.d("none", "none")
                }
                else{
                    y = y - 1
                    if (size == null) {
                        Log.d("none", "none")
                    }
                    else{
                        if (y != null) {
                            if (y > 0) {
                                if (y != null) {
                                    y = y - 1
                                    id = y
                                }
                            } else {
                                y = size - 1
                                id = y
                            }
                        }
                    }
                }
                if (player.isPlaying) {
                    player.stop()
                }
                player = MediaPlayer.create(context, Uri.parse(y?.let { it1 -> gaane?.get(it1) }))
                player.start()
                binding.artn1.text = y?.let { it1 -> artistname?.get(it1) } ?: toString()
                Log.d("artist name", "${binding.artn1.text}")
                binding.songname.text = y?.let { it1 -> musicname?.get(it1) } ?: toString()
                Log.d("song name", "${binding.songname.text}")
                //binding.imgalbum
                metaRetriver.setDataSource(y?.let { it1 -> albumcover?.get(it1) })
                var art: ByteArray? = metaRetriver.getEmbeddedPicture()
                if(art!=null){
                    binding.imgalbum.setImageBitmap(art.let {
                        art?.size?.let { it1 ->
                            BitmapFactory.decodeByteArray(art, 0, it1)

                        }
                    })
                }
                else{
                    binding.imgalbum.setImageResource(R.drawable.download)
                }

                if (y != null) {
                    y  = y + 1
                }
            }
        }
        // TODO: 16/07/21 1. previous crash 2. next crash 3. remove none none!! 4.  move frag logic part to viewmodel

        binding.forwad.setOnClickListener {

            var x: Int? = id
            if(player!=null) {
                binding.play.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                if (x == null) {
                   Log.d("none", "none")
                }
                else{
                    if (size == null) {
                        Log.d("none", "none")
                    }
                    else{
                        if (x != null) {
                            if (x < size - 1) {
                                if (x != null) {
                                    x = x + 1
                                    id = x
                                }
                            } else {
                                x = 0
                                id = x
                            }
                        }
                    }
                }
                if (player.isPlaying) {
                    player.stop()
                }
                player = MediaPlayer.create(context, Uri.parse(x?.let { it1 -> gaane?.get(it1) }))
                player.start()
                binding.artn1.text = x?.let { it1 -> artistname?.get(it1) } ?: toString()
                Log.d("artist name", "${binding.artn1.text}")
                binding.songname.text = x?.let { it1 -> musicname?.get(it1) } ?: toString()
                metaRetriver.setDataSource(x?.let { it1 -> albumcover?.get(it1) })
                var art: ByteArray? = metaRetriver.getEmbeddedPicture()
                if(art!=null){
                    binding.imgalbum.setImageBitmap(art.let {
                        art?.size?.let { it1 ->
                            BitmapFactory.decodeByteArray(art, 0, it1)

                        }
                    })
                }
                else{
                    binding.imgalbum.setImageResource(R.drawable.download)
                }
                Log.d("song name", "${binding.songname.text}")
                //binding.imgalbum


            }
        }

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
        player = MediaPlayer.create(context, Uri.parse(datafour))
        player.start()
        binding.play.setBackgroundResource(R.drawable.ic_baseline_pause_24)
        binding.imgalbum.startAnimation(animation)
        val seeeekbar: SeekBar = binding.seekBar
        if (datafive != null) {
            seeeekbar.max = datafive
        }
        seeeekbar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seeeekbar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                if(fromUser){
                    player.seekTo(progress)
                    seeeekbar.progress = progress
                }
            }

            override fun onStartTrackingTouch(seeeekba: SeekBar) {
            }

            override fun onStopTrackingTouch(seeeekba: SeekBar) {
                Toast.makeText(context,
                    "Progress is: " + seeeekba.progress + "%",
                    Toast.LENGTH_SHORT).show()
            }
        })
        var handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seeeekbar.progress = player.currentPosition
                    handler.postDelayed(this, 1000)
                }
                catch (e: Exception){
                    seeeekbar.progress = 0

                }

            }
        },0)
        return binding.root
    }

    fun onClick() {
            val musicintent = Intent(context, playmusic::class.java)
        musicintent.putExtra("my song path", datafour)
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
        requireActivity().startService(musicintent)
        if(!player.isPlaying){
            binding.imgalbum.startAnimation(animation)
            binding.play.setBackgroundResource(R.drawable.ic_baseline_pause_24)

        }
        else{
            player.pause()
            binding.imgalbum.clearAnimation()
            binding.play.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        }


    }
}