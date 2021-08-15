package com.nelsito.fifteenpuzzle

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import com.nelsito.fifteenpuzzle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.pieces.observe(this, { pieces ->
            pieces.forEach {
                val iv = ImageView(applicationContext)
                iv.setImageBitmap(it)
                binding.board.addView(iv)
            }
        })

        mainViewModel.onCreate(getDrawable(R.drawable.unsplash) as BitmapDrawable)
    }
}