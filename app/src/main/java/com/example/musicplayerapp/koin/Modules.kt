package com.example.musicplayerapp.koin

import android.content.Context
import com.example.musicplayerapp.repository.SongRepository
import com.example.musicplayerapp.viewModel.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MusicViewModel(get()) }
}
val repositoryModule = module {
    single {
        SongRepository()
    }
}