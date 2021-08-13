package com.nelsito.fifteenpuzzle.domain

data class Puzzle15(val tiles: List<Int>) {
    fun toList(): List<Int> {
        return tiles
    }

    private val size = 4

    //TODO: Extract movements as objects so when the player clicks a tile
    // it returns the proper movement to apply to the Puzzle
    fun down(): Puzzle15 {
        val blankIndex = tiles.indexOf(0)

        //only if blank tile is not at the top row: i.e. index >= size
        if (blankIndex < size) return Puzzle15(tiles)
        val targetIndex = blankIndex - size

        return moveTiles(blankIndex, targetIndex)
    }

    fun up(): Puzzle15 {
        val blankIndex = tiles.indexOf(0)

        //only if blank tile is not at the bottom row: i.e. index >= size
        if (blankIndex > (size * size - size)) return Puzzle15(tiles)
        val targetIndex = blankIndex + size

        return moveTiles(blankIndex, targetIndex)
    }

    fun right(): Puzzle15 {
        val blankIndex = tiles.indexOf(0)

        //only if blank tile is not at the first column: i.e. index % size = 0
        if (blankIndex % size == 0) return Puzzle15(tiles)
        val targetIndex = blankIndex - 1

        return moveTiles(blankIndex, targetIndex)
    }

    fun left(): Puzzle15 {
        val blankIndex = tiles.indexOf(0)

        //only if blank tile is not at the first column: i.e. index % size = size - 1
        if (blankIndex % size == size - 1) return Puzzle15(tiles)
        val targetIndex = blankIndex + 1

        return moveTiles(blankIndex, targetIndex)
    }

    private fun moveTiles(blankIndex: Int, targetIndex: Int): Puzzle15 {
        val newTiles = tiles.toMutableList().apply {
            this[blankIndex] = tiles[targetIndex]
            this[targetIndex] = 0
        }
        return Puzzle15(newTiles)
    }

}
