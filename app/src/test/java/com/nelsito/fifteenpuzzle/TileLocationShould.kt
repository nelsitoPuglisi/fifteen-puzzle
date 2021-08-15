package com.nelsito.fifteenpuzzle

import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TileLocationShould {
    @Test
    fun `locate first tile at 0,0`() {
        //given
        val layoutWidth = 1080
        val tile = Tile(1, 0, mock())
        //when
        val actual = tile.locate(layoutWidth)

        //then
        val expected = TileLocation(0, 0)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `locate second tile at second column`() {
        //given
        val layoutWidth = 1080
        val tile = Tile(2, 1, mock())
        //when
        val actual = tile.locate(layoutWidth)

        //then
        val expected = TileLocation(270, 0)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `locate fifth tile at second row and first column`() {
        //given
        val layoutWidth = 1080
        val tile = Tile(5, 4, mock())
        //when
        val actual = tile.locate(layoutWidth)

        //then
        val expected = TileLocation(0, 270)
        assertThat(actual).isEqualTo(expected)
    }
}