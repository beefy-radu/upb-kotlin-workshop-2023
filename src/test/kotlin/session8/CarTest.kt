package session8

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*

// Finish up CarTest
class CarTest {
    private lateinit var car: Car
    private lateinit var battery: Battery
    private lateinit var engine: Engine

    @BeforeEach
    fun setUp() {
        battery = mockk()
        engine = mockk()
        car = Car(battery, engine)
    }

    fun mockkExamples() {
        // how to throw an exception from a mock method call
        every { engine.start() } throws Exception()

        // how to return a value
        every { engine.running } returns true
    }

    @Test
    fun `given the engine was not running, when starting the car, then the engine will be running`() {
        // given

        // when

        // then
    }
}