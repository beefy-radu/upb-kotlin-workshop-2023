package session9

import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

class TestAries8AsDippable {
    private lateinit var dippable: Dippable

    @BeforeEach
    fun setUp() {
        dippable = Aries8()
    }
}