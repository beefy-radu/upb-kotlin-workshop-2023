package session5

import java.util.Date

// Exercise 1: Don't be mean!
fun mean(values: List<Double>): Double {
    return values.average()
}

// Exercise 2: Stress to the limit!
fun overlaps(range1: IntRange, range2: IntRange): Boolean {
    return range1.last > range2.first && range1.first < range2.last
}

// Exercise 3: Live in the present
fun isInThePast(date: Date, referenceNow: Date = Date()): Boolean {
    return referenceNow.time - date.time > 0
}