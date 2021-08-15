package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.core.view.allViews
import androidx.core.view.children
import com.nelsito.fifteenpuzzle.databinding.ActivityMainBinding
import com.nelsito.fifteenpuzzle.domain.None


class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.pieces.observe(this, { tiles ->
            binding.board.removeAllViews()
            tiles.forEach { bitmapTile ->
                if (bitmapTile.tile.startIndex < 15) {
                    val iv = ImageView(applicationContext)
                    iv.setImageBitmap(bitmapTile.bitmap)
                    iv.tag = bitmapTile.tile

                    val location = bitmapTile.tile.locate(binding.board.width)
                    val params = RelativeLayout.LayoutParams(binding.board.width / 4, binding.board.width / 4)
                    params.topMargin = location.top
                    params.leftMargin = location.left

                    iv.setOnClickListener { tileSelected(it) }
                    binding.board.addView(iv, params)
                }
            }
        })

        //Every time the puzzle is updated, the tiles are assigned to the ImageViews
        //So that each Tile has the proper movement to do...
        mainViewModel.currentPuzzle.observe(this, { puzzle ->
            binding.board.children.forEach { view ->
                 view.tag = puzzle.tiles.first {
                     (view.tag as Tile).correctNumber == it.correctNumber
                 }
            }
        })

        mainViewModel.loading.observe(this, {
            binding.btnStart.isEnabled = !it
            if (it) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
        })

        binding.btnStart.setOnClickListener {
            mainViewModel.start()
        }
    }

    private fun tileSelected(imageView: View) {
        val previousTile = imageView.tag as Tile
        if (previousTile.movement is None) return

        val newTile = mainViewModel.tileClicked(previousTile)
        //animate movement
        val newLocation = newTile.locate(binding.board.width)
        val params = imageView.layoutParams as RelativeLayout.LayoutParams
        params.topMargin = newLocation.top
        params.leftMargin = newLocation.left
        imageView.layoutParams = params

    }
}