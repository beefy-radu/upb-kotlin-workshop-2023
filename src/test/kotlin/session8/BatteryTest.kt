package session8

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
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
    private lateinit var console: Console

    @BeforeEach
    fun setUp() {
        console = mockk(relaxed = true)
    }

    @Test
    fun `given capacity is 0, when sparking the battery, then it will return false` () {
        // given capacity is 0
        val battery = Battery(0, console)

        // when sparking the battery
        val result = battery.spark()

        // then it will return false
        assertFalse(result)
    }

    @Test
    fun `given capacity is 0, when sparking the battery, then it will not decrement the capacity` () {
        // given capacity is 0
        val battery = Battery(0, console)

        // when sparking the battery
        battery.spark()

        // then it will not decrement the capacity
        assertEquals(0, battery.capacity)
    }

    @Test
    fun `given capacity is not 0, when sparking the battery, then it will return true` () {
        // given capacity is not 0
        val battery = Battery(20, console)

        // when sparking the battery
        val result = battery.spark()

        // then it will return true
        assertTrue(result)
    }

    @Test
    fun `given capacity is not 0, when sparking the battery, then it will decrement the capacity` () {
        // given capacity is 20
        val battery = Battery(20, console)

        // when sparking the battery
        battery.spark()

        // then it will not decrement the capacity
        assertEquals(19, battery.capacity)
    }

    @Test
    fun `given capacity is not 0, when sparking the battery, then it print to the console` () {
        // given capacity is 20
        val battery = Battery(20, console)

        // when sparking the battery
        battery.spark()

        // then it will not decrement the capacity
        verify {
            console.printBatteryStats(20, "\uD83D\uDD0B")
        }
    }
}