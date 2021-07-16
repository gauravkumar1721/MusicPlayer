package com.example.musicplayerapp.service

import android.app.Service
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import com.example.musicplayerapp.R
import com.example.musicplayerapp.databinding.FragmentPlaySongsBinding

class playmusic : Service() {
    lateinit var player: MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d("song start", "song start")
        val datafour = intent.getStringExtra("my song path")
        player = MediaPlayer.create(this, Uri.parse(datafour))
        player.setLooping(true)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        // stopping the process
        player.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}