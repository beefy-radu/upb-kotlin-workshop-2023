package session8

import kotlin.test.*

// FIRST
// F = Fast
// I = Independent
// R = Repeatable
// S = Self-validating
// T = Timely

// A test should only validate one single aspect
// You should never have testing code in your production code

class BatteryTest {
    @Test
    fun `given capacity is 0, when sparking the battery, then it will return false` () {
        // given capacity is 0
        val battery = Battery(0)

        // when sparking the battery
        val result = battery.spark()

        // then it will return false
        assertFalse(result)
    }

    @Test
    fun `given capacity is 0, when sparking the battery, then it will not decrement the capacity` () {
        // given capacity is 0
        val battery = Battery(0)

        // when sparking the battery
        battery.spark()

        // then it will not decrement the capacity
        assertEquals(0, battery.capacity)
    }

    @Test
    fun `given capacity is not 0, when sparking the battery, then it will return true` () {
        // given capacity is not 0
        val battery = Battery(20)

        // when sparking the battery
        val result = battery.spark()

        // then it will return true
        assertTrue(result)
    }

    @Test
    fun `given capacity is not 0, when sparking the battery, then it will decrement the capacity` () {
        // given capacity is 0
        val battery = Battery(20)

        // when sparking the battery
        battery.spark()

        // then it will not decrement the capacity
        assertEquals(19, battery.capacity)
    }
}