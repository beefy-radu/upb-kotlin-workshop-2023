package session8

import java.io.PrintStream
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
    val console: Console = ConsoleImpl()
    val battery = Battery(console = console)
    val engine = FuelEngine(battery, console)
    val car = Car(battery, engine, console)

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
    private val engine: Engine,
    private val console: Console
    )
{
    var speed: Int = 0
        private set

    val charged: Boolean
        get() = battery.capacity > 0    // Law of Demeter

    fun start() {
        engine.ignite()
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
        console.print { stream ->
            repeat(speed) {
                stream.print("\uD83D\uDE97")
            }
            stream.println()
        }
    }

    private fun printStop() {
        console.print {
            println("\uD83D\uDED1")
        }
    }
}

// ElectricEngine vs FuelEngine

abstract class Engine(private val battery: Battery, private val console: Console) {
    var running: Boolean = false
        private set

    open fun ignite() {
        if (running) {
            throw CarAlreadyStartedException()
        }
    }

    open fun start() {
        if (!battery.spark()) {
            throw IllegalStateException("Battery is dead")
        }
        running = true
        console.print { it.println("\uD83D\uDD25") }
    }

    fun stop() {
        running = false
    }
}

class ElectricEngine(private val battery: Battery, console: Console) : Engine(battery, console) {
    override fun ignite() {
        // We can do this as many times as we want
    }

    override fun start() {
        super.start()
        battery.spark()
    }
}

class FuelEngine(battery: Battery, console: Console) : Engine(battery, console) {
    var fuel: Int = 0
        private set

    fun fill(quantity: Int) {
        fuel += quantity
    }

    override fun start() {
        if (fuel <= 0) {
            throw IllegalStateException("Out of gas")
        } else {
            fuel--
        }
        super.start()
    }
}


interface Console {
    fun print(block: (PrintStream) -> Unit)
}

class ConsoleImpl : Console {
    override fun print(block: (PrintStream) -> Unit) {
        block(System.out)
    }
}

class Battery(initialCapacity: Int = MAX_CAPACITY, private val console: Console) {
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
        console.print { stream: PrintStream ->
            repeat(capacity) {
                stream.print("\uD83D\uDD0B")
            }
            stream.println()
        }
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