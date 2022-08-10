package com.codelearn.uzMusicPlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codelearn.uzMusicPlay.databinding.ActivityPlaylistBinding

class PlaylistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPinkNav)
        binding=ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}