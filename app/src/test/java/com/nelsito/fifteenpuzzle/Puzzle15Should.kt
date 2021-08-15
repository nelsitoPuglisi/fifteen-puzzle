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
    fun `move right from 11 to 0`() {
        //given
        val aPuzzle = aSamplePuzzle()
        //when
        val actual = aPuzzle.right()
        //then
        val expected = Puzzle15(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 13, 14, 0, 11))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `move down 15 then right 12 then up 11 to 0`() {
        //given
        val aPuzzle = aSamplePuzzle()
        //when
        val actual = aPuzzle.down().right().up()
        //then
        val expected = Puzzle15(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `move down 15 then right 12 then up 11 then left 15 to 0 `() {
        //given
        val aPuzzle = aSamplePuzzle()
        //when
        val actual = aPuzzle.down().right().up().left()
        //then
        val expected = Puzzle15(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0))
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
    https://martinfowler.com/bliki/ObjectMother.html
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

    //Every Puzzle15 already has allowed movements for some tiles.
    //
}