package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.service.quicksettings.TileService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nelsito.fifteenpuzzle.domain.Puzzle15


class MainViewModel : ViewModel() {

    private val _pieces = MutableLiveData<List<Tile>>()
    val pieces: LiveData<List<Tile>> get() = _pieces

    fun start(scaledBitmap: Bitmap) {
        //TODO: Load image from api
        val splitted = splitImage(scaledBitmap)

        //TODO: Create a shuffled puzzle
        val puzzle15 = aSamplePuzzle()

        _pieces.value = createTiles(puzzle15, splitted)
    }

    private fun createTiles(puzzle15: Puzzle15, splitted: List<Bitmap>): List<Tile> {
        val tiles = mutableListOf<Tile>()

        for (index in puzzle15.tiles.indices) {
            tiles.add(Tile(puzzle15.tiles[index], index, splitted[puzzle15.tiles[index]]))
        }

        return tiles
    }

    private fun aSamplePuzzle(): Puzzle15 {
        val tiles = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 13, 14, 11, 0)

        return Puzzle15(tiles)
    }

    private fun splitImage(bitmap: Bitmap): List<Bitmap> {

        val cols = 4
        val rows = 4
        val pieceWidth: Int = bitmap.width / cols
        val pieceHeight: Int = bitmap.height / rows

        val bitmapPieces = mutableListOf<Bitmap>()
        // Create each bitmap piece and add it to the resulting array
        var yCoord = 0
        for (row in 0 until rows) {
            var xCoord = 0
            for (col in 0 until cols) {
                bitmapPieces.add(Bitmap.createBitmap(bitmap, xCoord, yCoord, pieceWidth, pieceHeight))
                xCoord += pieceWidth
            }
            yCoord += pieceHeight
        }

        return bitmapPieces
    }


}

data class TileLocation(val right: Int, val top: Int)