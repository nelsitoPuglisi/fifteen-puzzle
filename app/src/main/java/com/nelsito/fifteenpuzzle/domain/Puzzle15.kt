package com.nelsito.fifteenpuzzle.domain

data class Puzzle15(val tiles: List<Int>) {
    fun toList(): List<Int> {
        return tiles
    }

    private val size = 4

    fun down(): Puzzle15 {
        //only if blank tile is not at the top row: i.e. index >= size
        val blankIndex = tiles.indexOf(0)
        if (blankIndex <=3) return Puzzle15(tiles)
        val targetIndex = blankIndex - size

        val newTiles = tiles.toMutableList().apply {
            this[blankIndex] = tiles[targetIndex]
            this[targetIndex] = 0
        }
        return Puzzle15(newTiles)
    }

    fun right(): Puzzle15 {
        //only if blank tile is not at the first column: i.e. index % size > 0
        val blankIndex = tiles.indexOf(0)
        if (blankIndex % size == 0) return Puzzle15(tiles)

        val targetIndex = blankIndex - 1

        val newTiles = tiles.toMutableList().apply {
            this[blankIndex] = tiles[targetIndex]
            this[targetIndex] = 0
        }
        return Puzzle15(newTiles)
    }

}
