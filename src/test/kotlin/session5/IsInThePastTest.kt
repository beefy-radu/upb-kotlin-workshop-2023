package session5

import java.text.SimpleDateFormat
import java.util.Date
import kotlin.test.*

class IsInThePastTest {
    @Test
    fun `given a date in the past, when checking if it is in the past, then the result will be true`() {
        // given
        val dateInThePast = SimpleDateFormat("DD-MM-YY").parse("23-05-88")

        // when
        val result = isInThePast(dateInThePast)

        // then
        assertTrue(result)
    }

    @Test
    fun `given a date in the present, when checking if it is in the past, then the result will be false`() {
        // given
        val dateInThePresent = Date()

        // when
        val result = isInThePast(dateInThePresent)

        // then
        assertFalse(result)
    }

    @Test
    fun `given a date in the future, when checking if it is in the past, then the result will be false`() {
        // given
        val myNow = SimpleDateFormat("DD-MM-YY").parse("23-05-88")

        val dateInTheFuture = SimpleDateFormat("DD-MM-YYYY").parse("23-05-2002")

        // when
        val result = isInThePast(dateInTheFuture, referenceNow = myNow)

        // then
        assertFalse(result)
    }
}