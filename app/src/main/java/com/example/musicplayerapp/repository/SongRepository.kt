package com.example.musicplayerapp.repository

import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import android.util.Log
import com.example.musicplayerapp.data.Music
import java.time.Duration

//todo remove context from constructor, pass it to through method
class SongRepository() {

        fun SongNameData(context: Context): ArrayList<Music> {
            val music: ArrayList<Music> = ArrayList()
            val gaane: ArrayList<String> = ArrayList()
            val songname: ArrayList<String> = ArrayList()
            val artistname: ArrayList<String> = ArrayList()
            val songpath: ArrayList<String> = ArrayList()

            var id: Int =0
            var songs: Cursor? = context?.contentResolver?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    null)

            val metaRetriver: MediaMetadataRetriever = MediaMetadataRetriever()
            lateinit var musics: Music
            if (songs != null && songs.count > 0) {
                if (songs.moveToFirst()) {
                    do {
                        val songsName: String? =
                            songs.getString(songs.getColumnIndex(MediaStore.Audio.Media.TITLE))
                        val songDuration: Int? = songs.getInt(songs.getColumnIndex(MediaStore.Audio.Media.DURATION))
                        var artistName: String? =
                            songs.getString(songs.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST))
                        val songPath: String? =
                            songs.getString(songs.getColumnIndex(MediaStore.Audio.AudioColumns.DATA))
                        metaRetriver.setDataSource(songPath)
                        Log.d("song path", "song path $songPath")
                        if(songsName!=null){
                            songname.add(id, songsName)
                            Log.d("songsName", " songs $songsName")
                        }
                        else{
                            songname.add(id, "God's Plan")
                            Log.d("songsName", " songnull")
                        }
                        if(artistName!=null)
                        {
                            artistname.add(id, artistName)
                            Log.d("ArtistName", " Artist $artistName")
                        }
                        else{
                            val po: String = "Alec"
                            artistname.add(id, po)
                            Log.d("ArtistName", "$po")
                            artistName = po
                        }
                        Log.d("songDuration", " Duration $songDuration")
                        var art: ByteArray? = metaRetriver.getEmbeddedPicture()
                        if (art != null) {

                            Log.d("got the image", "image: ${art.size}")
                        } else {
                            Log.d("didn't get the image", "no image")
                        }

                        if (songPath != null) {
                            gaane.add(id, songPath)
                            songpath.add(id, songPath)
                            Log.d("my song", "my song ${gaane[id]}")
                            id = id + 1
                        }
                        musics = Music(id,songsName, artistName, art, songPath, songDuration, gaane, songname, artistname, songpath)
                        music.add(musics)
                    } while (songs.moveToNext())
                }
                songs.close()

            }
            return music
        }

}