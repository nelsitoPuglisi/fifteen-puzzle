package com.nelsito.fifteenpuzzle.domain

import android.graphics.Bitmap
import com.nelsito.fifteenpuzzle.Tile

data class Puzzle15(val tilesPosition: List<Int>, val size: Int = 4) {
    fun toList(): List<Int> {
        return tilesPosition
    }
    val tiles = mutableListOf<Tile>()

    private val blankIndex = tilesPosition.indexOf(0)

    init {

        //tile 0 is blank
        //tile 1 should be index 0
        for (index in tilesPosition.indices) {
            if (tilesPosition[index] > 0) {
                tiles.add(createTile(index))
            }
        }
    }

    //TODO: Extract movements as objects so when the player clicks a tile
    // it returns the proper movement to apply to the Puzzle
    fun down(): Puzzle15 {
        //only if blank tile is not at the top row: i.e. index >= size
        if (blankIndex < size) return Puzzle15(tilesPosition)
        val targetIndex = blankIndex - size

        return moveTiles(blankIndex, targetIndex)
    }

    fun up(): Puzzle15 {
        //only if blank tile is not at the bottom row: i.e. index >= size
        if (blankIndex > (size * size - size)) return Puzzle15(tilesPosition)
        val targetIndex = blankIndex + size

        return moveTiles(blankIndex, targetIndex)
    }

    fun right(): Puzzle15 {
        //only if blank tile is not at the first column: i.e. index % size = 0
        if (blankIndex % size == 0) return Puzzle15(tilesPosition)
        val targetIndex = blankIndex - 1

        return moveTiles(blankIndex, targetIndex)
    }

    fun left(): Puzzle15 {
        //only if blank tile is not at the first column: i.e. index % size = size - 1
        if (blankIndex % size == size - 1) return Puzzle15(tilesPosition)
        val targetIndex = blankIndex + 1

        return moveTiles(blankIndex, targetIndex)
    }

    private fun moveTiles(blankIndex: Int, targetIndex: Int): Puzzle15 {
        val newTiles = tilesPosition.toMutableList().apply {
            this[blankIndex] = tilesPosition[targetIndex]
            this[targetIndex] = 0
        }
        return Puzzle15(newTiles)
    }

    private fun createTile(index: Int) : Tile {
        var movement :Movement= None()
        if (blankIndex == index + size) // this tile can go down
            movement = Down()
        if (blankIndex == index - size)
            movement = Up()
        if (blankIndex == index + 1 && index % size < size - 1)
            movement = Right()
        if (blankIndex == index - 1 && index % size > 0)
            movement = Left()
        return Tile(tilesPosition[index], index, movement)
    }

    fun isSolved(): Boolean {
        return tilesPosition == listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)
    }

}

class Left : Movement {
    override fun move(puzzle15: Puzzle15): Puzzle15 {
        return puzzle15.left()
    }

}

class Right : Movement {
    override fun move(puzzle15: Puzzle15): Puzzle15 {
        return puzzle15.right()
    }
}

interface Movement {
    fun move(puzzle15: Puzzle15): Puzzle15
}

class Up : Movement {
    override fun move(puzzle15: Puzzle15): Puzzle15 {
        return puzzle15.up()
    }
}

class Down : Movement {
    override fun move(puzzle15: Puzzle15): Puzzle15 {
        return puzzle15.down()
    }
}

class None : Movement {
    override fun move(puzzle15: Puzzle15): Puzzle15 {
        return puzzle15
    }

}
