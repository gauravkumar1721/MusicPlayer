package com.example.musicplayerapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayerapp.data.Music
import com.example.musicplayerapp.repository.SongRepository

class MusicViewModel(private val repo: SongRepository): ViewModel() {
    val msongName: MutableLiveData<ArrayList<Music>> = MutableLiveData()
    val livedata: LiveData<ArrayList<Music>> = msongName


    fun ContactViewModel(context: Context) {
        getSongsN(context)
    }

    fun getSongsN(context: Context) {
        msongName.postValue(repo.SongNameData(context))
    }


}