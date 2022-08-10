package com.codelearn.uzMusicPlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codelearn.uzMusicPlay.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPinkNav)
        binding=ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}