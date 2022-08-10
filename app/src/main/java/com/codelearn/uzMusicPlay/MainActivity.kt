package com.codelearn.uzMusicPlay

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.codelearn.uzMusicPlay.Adapter.MusicAdapter
import com.codelearn.uzMusicPlay.Model.Music
import com.codelearn.uzMusicPlay.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var musicAdapter:MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializaLayout()

        binding.shuffleBtn.setOnClickListener(this)
        binding.favouriteBtn.setOnClickListener(this)
        binding.playlistBtn.setOnClickListener(this)

        binding.navView.setNavigationItemSelectedListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.shuffleBtn->startActivity(Intent(this@MainActivity,PlayerActivity::class.java))
            R.id.favouriteBtn->startActivity(Intent(this@MainActivity,FavoriteActivity::class.java))
            R.id.playlistBtn->startActivity(Intent(this@MainActivity,PlaylistActivity::class.java))
        }
    }
    private fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==13)
        {
            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            }
            else
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navFeedback->Toast.makeText(baseContext,"FeedBack",Toast.LENGTH_SHORT).show()
            R.id.navAbout->Toast.makeText(baseContext,"About",Toast.LENGTH_SHORT).show()
            R.id.navSettings->Toast.makeText(baseContext,"Setting",Toast.LENGTH_SHORT).show()
            R.id.navExit-> exitProcess(1)
        }
        return true
    }
    private fun initializaLayout(){
        requestRuntimePermission()
        setTheme(R.style.coolPinkNav)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle=ActionBarDrawerToggle(this,binding.root,R.string.open,R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val musicList=ArrayList<String>()
        musicList.add("1 Song Name")
        musicList.add("2 Song Name")
        musicList.add("2 Song Name")

        binding.musicRV.setHasFixedSize(true)
        binding.musicRV.setItemViewCacheSize(13)
        binding.musicRV.layoutManager=LinearLayoutManager(this@MainActivity)
        musicAdapter= MusicAdapter(this@MainActivity, musicList)
        binding.musicRV.adapter=musicAdapter
        binding.totalSong.text="Total Songs : "+musicAdapter.itemCount
    }
    @SuppressLint("Range")
    private fun getAllAduio():ArrayList<Music>{
        val tempList=ArrayList<Music>()
        val selection=MediaStore.Audio.Media.IS_MUSIC+" !=0"
        val projection= arrayOf(MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA)
        val cursor=this.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            MediaStore.Audio.Media.DATE_ADDED+" DESC",
            null
        )
        if (cursor!=null)
        {
            if (cursor.moveToFirst())
            {
                do{
                    val titleC=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                }
                    while (cursor.moveToNext())
            }
        }
        return tempList
    }
}