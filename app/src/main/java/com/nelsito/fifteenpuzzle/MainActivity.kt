package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
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

        mainViewModel.pieces.observe(this, { tiles ->
            /*binding.tile1.setImageBitmap(pieces[0])
            binding.tile2.setImageBitmap(pieces[1])
            binding.tile3.setImageBitmap(pieces[2])
            binding.tile4.setImageBitmap(pieces[3])
            binding.tile5.setImageBitmap(pieces[4])
            binding.tile6.setImageBitmap(pieces[5])
            binding.tile7.setImageBitmap(pieces[6])
            binding.tile8.setImageBitmap(pieces[7])
            binding.tile9.setImageBitmap(pieces[8])
            binding.tile10.setImageBitmap(pieces[9])
            binding.tile11.setImageBitmap(pieces[10])
            binding.tile12.setImageBitmap(pieces[11])
            binding.tile13.setImageBitmap(pieces[12])
            binding.tile14.setImageBitmap(pieces[13])
            binding.tile15.setImageBitmap(pieces[14])*/
            tiles.forEach {
                val iv = ImageView(applicationContext)
                iv.setImageBitmap(it.bitmap)
                iv.tag = it

                it.startIndex % 4
                val right =
                (iv.layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.ALIGN_RIGHT, )

                binding.board.addView(iv)
            }
            //TODO position each image as the puzzle suggest

        })


        binding.btnStart.setOnClickListener {
            //TODO: load from unsplash api
            val bitmapDrawable = getDrawable(R.drawable.unsplash) as BitmapDrawable
            mainViewModel.start(cropAndScale(bitmapDrawable.bitmap))
        }
    }

    private fun cropAndScale(bitmap: Bitmap): Bitmap {
        val cropped = crop(bitmap)
        //TODO Scale image to square relative layout size
        Log.d("SCALE", "cropped.w: " + cropped.width)
        Log.d("SCALE", "cropped.h: " + cropped.height)
        Log.d("SCALE", "layout.w: " + binding.board.width)
        Log.d("SCALE", "layout.h: " + binding.board.height)
        return cropped
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