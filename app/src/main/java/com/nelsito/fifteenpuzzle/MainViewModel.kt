package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelsito.fifteenpuzzle.domain.Puzzle15
import com.nelsito.fifteenpuzzle.domain.Shuffler
import com.nelsito.fifteenpuzzle.infrastructure.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val photoRepository = PhotoRepository()

    private val _pieces = MutableLiveData<List<BitmapTile>>()
    val pieces: LiveData<List<BitmapTile>> get() = _pieces

    private val _solved = MutableLiveData(false)
    val solved: LiveData<Boolean> get() = _solved

    private val _backgroundColor = MutableLiveData<String>()
    val backgroundColor: LiveData<String> get() = _backgroundColor

    private val shuffler = Shuffler()

    //TODO: Create a shuffled puzzle
    private var puzzle15 = shuffler()
    private val _currentPuzzle = MutableLiveData(puzzle15)

    val currentPuzzle: LiveData<Puzzle15> get() = _currentPuzzle
    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> get() = _loading

    fun start() {

        _loading.value = true

        puzzle15 = shuffler()

        viewModelScope.launch {
            val photo = photoRepository.getRandom()

            _backgroundColor.value = photo.color

            val bitmap = async(Dispatchers.IO) { photoRepository.getBitmap(photo.urls.regular) }
            val splitted = splitImage(bitmap.await())

            _pieces.value = puzzle15.tiles.map {
                BitmapTile(splitted[it.correctNumber - 1], it)
            }

            _loading.value = false
        }
    }

    fun tileClicked(tile: Tile) : Tile {
        puzzle15 = tile.movement.move(puzzle15)
        _currentPuzzle.value = puzzle15

        //if it's solved finish
        _solved.value = puzzle15.isSolved()

        return puzzle15.tiles.first { tile.correctNumber == it.correctNumber }
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

data class BitmapTile(val bitmap: Bitmap, val tile: Tile)

data class TileLocation(val left: Int, val top: Int)