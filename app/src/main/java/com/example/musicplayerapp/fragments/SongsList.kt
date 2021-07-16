package com.example.musicplayerapp.fragments

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.R
import com.example.musicplayerapp.databinding.FragmentSongsListBinding
import com.example.musicplayerapp.viewModel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayerapp.adapter.SongAdapter
import com.example.musicplayerapp.data.Music
import com.example.musicplayerapp.repository.SongRepository


class SongsList : Fragment(), SongAdapter.SongAdapterInterface {
    var NavCont: NavController?=null
    val rc = 100
    lateinit var binding: FragmentSongsListBinding
    var size: Int = 0
    val viewModelinstance: MusicViewModel by viewModel()
    lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_songs_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //NavCont = Navigation.findNavController(view)
        context?.let { viewModelinstance.ContactViewModel(it) }
        NavCont = Navigation.findNavController(view)
        storagePermission(Manifest.permission.READ_EXTERNAL_STORAGE, rc)
        MySongData()
    }

    private fun MySongData(){
        val rc = binding.rcv
        val songnameObserver = Observer { newData: ArrayList<Music>? ->
            newData?.let { mysongData(it) }
        }

        viewModelinstance.livedata.observe(viewLifecycleOwner, songnameObserver)

    }
    private fun mysongData(songnameResponse: ArrayList<Music>) {
     val adapt = SongAdapter(songnameResponse)
        adapt.setData(this)
        size = songnameResponse.size
        binding.rcv.adapter = adapt
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun viewholdData(id: Int?,songname: String?, artistname: String?, albumimg: ByteArray?, songPath: String?, songDuration: Int?, gaane: ArrayList<String>, musicname: ArrayList<String>, singername: ArrayList<String>, songpath: ArrayList<String>) {
        Log.d("gdgd", "dgdfv")
        val bundle = bundleOf(Pair("id", id),Pair("my songName",songname), Pair("my artistName",artistname), Pair("my img", albumimg), Pair("song path", songPath), Pair("song duration", songDuration), Pair("song size",size ),Pair("gaane", gaane), Pair("musicname", musicname), Pair("artistname", singername), Pair("albumcover", songpath) )

        NavCont?.navigate(R.id.action_songsList_to_playSongs, bundle)
    }
    private fun storagePermission(permission: String, rc: Int){
        if(context?.let { ContextCompat.checkSelfPermission(it, permission) } == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), rc)
        }
        else{
            Toast.makeText(context, "Permission Granted Already", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == rc){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

