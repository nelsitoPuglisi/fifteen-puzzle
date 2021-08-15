package com.nelsito.fifteenpuzzle.domain

import kotlin.random.Random

class Shuffler(val size: Int = 4) {

    private val nbTiles = size * size - 1
    private var blankPos: Int = 15
    private var tiles = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)

    operator fun invoke() : Puzzle15 {
        do {
            reset() // reset in intial state
            shuffle() // shuffle
        } while (!isSolvable()) // make it until grid be solvable
        return Puzzle15(tiles)
    }

    private fun reset() {
        for (i in 0 until tiles.size) {
            tiles[i] = (i + 1) % tiles.size
        }
        // we set blank cell at the last
        blankPos = tiles.size - 1
    }

    private fun shuffle() {
        // don't include the blank tile in the shuffle, leave in the solved position
        var n: Int = nbTiles
        while (n > 1) {
            val r: Int = Random.nextInt(n--)
            val tmp: Int = tiles[r]
            tiles[r] = tiles[n]
            tiles[n] = tmp
        }
    }

    // Only half permutations o the puzzle are solvable
    // Whenever a tile is preceded by a tile with higher value it counts
    // as an inversion. In our case, with the blank tile in the solved position,
    // the number of inversions must be even for the puzzle to be solvable
    private fun isSolvable(): Boolean {
        var countInversions = 0
        for (i in 0 until nbTiles) {
            for (j in 0 until i) {
                if (tiles[j] > tiles[i]) countInversions++
            }
        }
        return countInversions % 2 == 0
    }

    fun isSolved(): Boolean {
        if (tiles[tiles.size - 1] != 0) // if blank tile is not in the solved position ==> not solved
            return false
        for (i in nbTiles - 1 downTo 0) {
            if (tiles[i] != i + 1) return false
        }
        return true
    }
}