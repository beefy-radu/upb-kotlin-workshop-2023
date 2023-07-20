package session5

import kotlin.test.*

class MeanTest {
    @Test
    fun `given no numbers, when computing the mean, then the result will be NaN`() {
        // given
        val given = listOf<Double>()

        // when
        val result = mean(given)

        // then
        assertEquals(expected = Double.NaN, actual = result)
    }

    @Test
    fun `given the same numbers multiple times, when computing the mean, then the result will be that exact number`() {
        // given
        val given = listOf<Double>(1.0, 1.0, 1.0, 1.0)

        // when
        val result = mean(given)

        // then
        assertEquals(expected = 1.0, actual = result)
    }

    @Test
    fun `given an odd number followed by an even numbers of zeros, when computing the mean, then the result will be checked by rounding`() {
        // given
        val given = listOf(1.0, 0.0, 0.0)

        // when
        val result = mean(given)

        // then
        assertEquals(expected = 0.3333, actual = result, absoluteTolerance = 0.0001)
    }
}