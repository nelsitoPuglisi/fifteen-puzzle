package com.nelsito.fifteenpuzzle

import android.graphics.Bitmap

data class Tile(val correctNumber: Int, val startIndex: Int, val bitmap: Bitmap) {
    fun locate(boardSize: Int): TileLocation {
        val tileSize = boardSize / 4 //4 cols. Squared board
        val tileCol = startIndex % 4
        val tileRow = startIndex / 4
        return TileLocation(tileSize * tileCol, tileSize * tileRow )
    }
}