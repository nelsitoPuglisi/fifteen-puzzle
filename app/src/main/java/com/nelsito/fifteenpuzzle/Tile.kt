package com.nelsito.fifteenpuzzle

import com.nelsito.fifteenpuzzle.domain.Movement
import com.nelsito.fifteenpuzzle.domain.None

data class Tile(val correctNumber: Int, val startIndex: Int, val movement: Movement = None()) {
    fun locate(boardSize: Int): TileLocation {
        val tileSize = boardSize / 4 //4 cols. Squared board
        val tileCol = startIndex % 4
        val tileRow = startIndex / 4
        return TileLocation(tileSize * tileCol, tileSize * tileRow )
    }
}