package com.codelearn.uzMusicPlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codelearn.uzMusicPlay.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPinkNav)
        binding=ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}