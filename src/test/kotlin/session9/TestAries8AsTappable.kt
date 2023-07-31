package session9

import kotlin.test.*
import org.junit.jupiter.api.BeforeEach

class TestAries8AsTappable {
    private lateinit var tappable: Tappable

    @BeforeEach
    fun setUp() {
        tappable = Aries8()
    }
}