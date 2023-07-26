package session8

import java.lang.IllegalStateException
import kotlin.RuntimeException

// SOLID principles
// S: Single Responsibility Principle (SIP) => a class, a method should have a single responsibility
// O: Open-Closed Principle (OCP) => a class should be open for extension, and closed for implementation
// L: Liskov Substitution Principle (LSP) => a child class must be compatible with the parent class
// I: Interface Segregation Principle (ISP) => interfaces should be split up by responsibility
// D: Dependency Inversion Principle (DIP) => higher level classes should not depend on lower level classes, rather both should depend on abstractions

// Single Responsibility Principle
// 🔥 + 🚗 + 🛑 + 🔋

// Law of Demeter
// Given a class C and a method M, then M should be able to access:
// * this
// * members of this
// * method arguments
// * objects created in this method
// * global constants (constants visible in its scope)

// More colloquially:
// Only talk to your immediate friends
// Don't talk to strangers

fun main() {
    // Manual dependency injection
    val battery = Battery()
    val engine = Engine(battery)
    val car = Car(battery, engine)

    // this can be automated: dependency injection framewoks (Dagger, Koin, Kodein)

    for (scenario in 0..20) {
        println("Starting scenario #${scenario + 1}")
        car.start()

        val steps = (10..30).random()
        for (step in 1..steps) {
            val seed = (1..100).random()
            if (seed % 2 == 0) {
                car.accelerate()
            } else {
                car.decelerate()
            }
        }

        car.stop()
    }
}

class CarAlreadyStartedException : RuntimeException("Car is already started")

class CarBatteryDeadException : RuntimeException("Car battery is dead")

class Car(
    private val battery: Battery,
    private val engine: Engine
    )
{
    var speed: Int = 0
        private set

    val charged: Boolean
        get() = battery.capacity > 0    // Law of Demeter

    fun start() {
        if (engine.running) {
            throw CarAlreadyStartedException()
        }
        try {
            engine.start()
        } catch(exception: IllegalStateException) {
            throw CarBatteryDeadException()
        }
    }

    fun accelerate() {
        if (engine.running) {
            speed++
        }
        printSpeed()
    }

    fun decelerate() {
        if (speed > 0) {
            speed--
        }
        printSpeed()
    }

    fun stop() {
        engine.stop()
        printStop()
        decelerateToStop()
    }

    private fun decelerateToStop() {
        while (speed > 0) {
            decelerate()
        }
    }

    private fun printSpeed() {
        repeat(speed) {
            print("\uD83D\uDE97")
        }
        println()
    }

    private fun printStop() {
        println("\uD83D\uDED1")
    }
}

class Engine(private val battery: Battery) {
    var running: Boolean = false
        private set

    fun start() {
        if (!battery.spark()) {
            throw IllegalStateException("Battery is dead")
        }
        running = true
        println("\uD83D\uDD25")
    }

    fun stop() {
        running = false
    }
}

class Battery(initialCapacity: Int = MAX_CAPACITY) {
//    var capacity: Int = 20  // Never use magic constants
//        private set
    var capacity: Int = initialCapacity
        private set

    fun spark(): Boolean {
        return if (capacity > 0) {
            printSparks()
            capacity--
            true
        } else {
            false
        }
    }

    private fun printSparks() {
        repeat(capacity) {
            print("\uD83D\uDD0B")
        }
        println()
    }

    companion object {
        private const val MAX_CAPACITY = 20
    }
}

// Bibliography
// https://en.wikipedia.org/wiki/Single-responsibility_principle
// https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html
// https://en.wikipedia.org/wiki/Test_double
// https://www.educative.io/answers/what-is-faking-vs-mocking-vs-stubbing
// https://martinfowler.com/articles/mocksArentStubs.html
// https://mockk.io/
// https://www.baeldung.com/kotlin/mockk