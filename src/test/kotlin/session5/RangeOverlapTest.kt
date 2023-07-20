package session5

import kotlin.test.*

class RangeOverlapTest {
    @Test
    fun `given range 1 is before range 2, when checking if they overlap, then the result will be false`() {
        // given
        val range1 = (0..10)
        val range2 = (11..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(false, result)
    }

    @Test
    fun `given range 1 is right before range 2, when checking if they overlap, then the result will be false`() {
        // given
        val range1 = (0..10)
        val range2 = (10..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(false, result)
    }

    @Test
    fun `given range 1 overlaps with the start of range 2, when checking if they overlap, then the result will be true`() {
        // given
        val range1 = (0..20)
        val range2 = (11..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `given range 1 is contained in range 2, when checking if they overlap, then the result will be true`() {
        // given
        val range1 = (60..70)
        val range2 = (11..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `given range 1 contains range 2, when checking if they overlap, then the result will be true`() {
        // given
        val range1 = (11..200)
        val range2 = (60..70)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `given range 1 overlaps on the end of range 2, when checking if they overlap, then the result will be true`() {
        // given
        val range1 = (190..210)
        val range2 = (11..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `given range 1 is after range 2, when checking if they overlap, then the result will be false`() {
        // given
        val range1 = (205..210)
        val range2 = (11..200)

        // when
        val result = overlaps(range1, range2)

        // then
        assertEquals(false, result)
    }
}