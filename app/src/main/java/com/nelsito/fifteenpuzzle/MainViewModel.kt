package com.nelsito.fifteenpuzzle

import android.R.attr
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.R.attr.bitmap




class MainViewModel : ViewModel() {

    private val _pieces = MutableLiveData<List<Bitmap>>()
    val pieces: LiveData<List<Bitmap>> get() = _pieces

    fun onCreate(bitmapDrawable: BitmapDrawable) {
        //load bitmap
        //load game with bitmap
        _pieces.value = splitImage(bitmapDrawable.bitmap)
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