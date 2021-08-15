package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.viewModels
import com.nelsito.fifteenpuzzle.databinding.ActivityMainBinding
import android.util.DisplayMetrics




class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.pieces.observe(this, { tiles ->
            tiles.forEach {
                if (it.startIndex < 15) {
                    val iv = ImageView(applicationContext)
                    iv.setImageBitmap(it.bitmap)
                    iv.tag = it

                    val location = it.locate(binding.board.width)
                    val params = RelativeLayout.LayoutParams(binding.board.width / 4, binding.board.width / 4)
                    params.topMargin = location.top
                    params.leftMargin = location.left
                    binding.board.addView(iv, params)
                }
            }
        })


        binding.btnStart.setOnClickListener {
            //TODO: load from unsplash api
            val bitmapDrawable = getDrawable(R.drawable.unsplash) as BitmapDrawable
            mainViewModel.start(crop(bitmapDrawable.bitmap))
        }
    }

    private fun crop(bitmap: Bitmap): Bitmap {
        if (bitmap.width >= bitmap.height){
            return Bitmap.createBitmap(
                bitmap,
                bitmap.width /2 - bitmap.height /2,
                0,
                bitmap.height,
                bitmap.height
            )
        }else{
            return Bitmap.createBitmap(
                bitmap,
                0,
                bitmap.height /2 - bitmap.width /2,
                bitmap.width,
                bitmap.width
            )
        }
    }
}