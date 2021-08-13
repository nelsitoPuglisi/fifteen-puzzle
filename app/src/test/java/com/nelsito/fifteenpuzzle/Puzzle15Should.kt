package com.nelsito.fifteenpuzzle

import com.nelsito.fifteenpuzzle.domain.Puzzle15
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Puzzle15Should {
    @Test
    fun `be presented as a list of integers`() {
        //given
        val aPuzzle = Puzzle15((0..15).toList())
        //when
        val actual = aPuzzle.toList()
        //then
        val expected = (0..15).toList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `move down from 15 to 0`() {
        //given
        val aPuzzle = aSamplePuzzle()
        //when
        val actual = aPuzzle.down()
        //then
        val expected = Puzzle15(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 0, 13, 14, 11, 15))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `not move down if blank is at top row`() {
        //given
        val tiles = listOf(1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 12, 15, 13, 14, 11)
        val aPuzzle = Puzzle15(tiles)
        //when
        val actual = aPuzzle.down()
        //then
        val expected = Puzzle15(tiles)
        assertThat(actual).isEqualTo(expected)
    }

    /*
    * returns a Puzzle with this form
    *  1  2  3  4
    *  5  6  7  8
    *  9 10 12 15
    * 13 14 11  0
    */
    private fun aSamplePuzzle(): Puzzle15 {
        val tiles = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 13, 14, 11, 0)

        return Puzzle15(tiles)
    }
}