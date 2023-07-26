package session8

import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

// Testing Doubles:
// Fake: real objects that simplify the implementation by returning test data
// Stub: objects that implement the public contract of a class, but only return defaults
// Mock: an object pretending to be a class, that allows us to control what happens when methods are called, and also records all of the method calls

// Mockk always creates non-relaxed mocks
// Mockito always created relaxed mocks

// Junit unit has the following annotations:
// @BeforeAll   => runs before all tests
// @BeforeEach  => runs before each test
// @Test        => runs test
// @AfterEach   => runs after each test
// @AfterAll    => runs after all tests

class EngineTest {
    private lateinit var battery: Battery
    private lateinit var engine: Engine

    @BeforeEach
    fun setUp() {
        battery = mockk()
        engine = Engine(battery)
    }

    @Test
    fun `when starting the engine, then the battery will be sparked`() {
        // given
        every { battery.spark() } returns true

        // when
        engine.start()

        // then
        verify { battery.spark() }
    }

    @Test
    fun `given battery can spark, when starting, then the engine will be running`() {
        // given battery can spark
        every { battery.spark() } returns true

        // when starting
        engine.start()

        // then the engine will be running
        assertTrue(engine.running)
    }

    @Test
    fun `given battery can't spark, when starting, then the engine will throw an Exception`() {
        // given battery can't spark
        every { battery.spark() } returns false

        // when starting
        val exception = assertThrows<IllegalStateException> {
            engine.start()
        }

        // then the engine will throw an Exception
        assertEquals("Battery is dead", exception.message)
    }

    @Test
    fun testStop() {
        // given

        // when

        // then
    }
}