package session9

import session8.Battery
import java.lang.IllegalStateException

// Open-Closed Principle: software entities (classes, modules, functions, etc.) should be open for extension,
// but closed for modification

// Ok... but what do "open" and "closed" mean for us?
// open means somebody can change it from the outside world => in OOP this means to extend it
// => us, as developers, need to figure out what we want others to be able to change and create abstractions for them
// (abstractions are inherently open)
// because closed means only we can change that code


// Liskov Substitution Principle: objects of a superclass should be replaceable with objects of its subclasses without
// breaking the application

enum class FuelType {
    Gas,
    Electric
}

interface Powered {
    val fuelType: FuelType
    val hasFuel: Boolean
}

class Gas : Powered {
    override val fuelType: FuelType
        get() = FuelType.Gas

    var fuel: Int = 0
        private set

    override val hasFuel: Boolean
        get() = fuel > 0

    fun fill(quantity: Int) {
        fuel += quantity
    }
}

class Electric(private val battery: Battery) : Powered {
    override val fuelType: FuelType
        get() = FuelType.Electric

    override val hasFuel: Boolean
        get() = battery.capacity > 0
}

interface Ignitable {
    val battery: Battery

    fun ignite() { }
}

abstract class Engine : Powered, Ignitable {
    var running: Boolean = false
        private set

    fun start() {
        if (hasFuel) {
            throw IllegalStateException("Out of fuel")
        }
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

// Could we achieve multiple inheritance? Let's delegate this....
class ElectricEngine(override val battery: Battery) : Engine(), Powered by Electric(battery)

class GasEngine(override val battery: Battery) : Engine(), Powered by Gas()


// Let's go to math: if S is a subtype of T, then objects of type T in a program may be replaced with objects of type S
// without altering any of the desirable properties of that program (e.g. correctness)

// Let's understand type variance a bit...
// Variance: A <= B, which means: A is a child of B (or B)  => IS A relation (as opposed to HAS A relation)
// Covariance: a Child class is covariant with a Parent class
// Contravariance: a Parent class is contravariant with a Child class
// Invariance: two siblings classes are invariant

// Composition:
open class A

class B {
    val a: A = A()  // HAS A relation
}

class C : A()   // IS A relation

class D : A()

// C & D are invariant

// Corollary: In addition to the signature requirements, the subtype must meet a number of behavioural conditions:
// 1. Pre-conditions cannot be strengthened in the subtype.
// 2. Post-conditions cannot be weakened in the subtype.

// What are pre-conditions?
// What are post-conditions?

open class GrandParent

open class Parent : GrandParent()

class Child : Parent() {
    fun otherBehavior() {}
}

class Child2: Parent()

val func1: (Parent) -> Parent = { arg: Parent ->
    Parent()
}

val func2: (Parent) -> Parent = { arg: Parent ->
    Child()
}

val func3: (Parent) -> Child = { arg: Parent ->
    Child()
}
val func3to2: (Parent) -> Parent = func3

val func4: (Parent) -> Parent = { arg: GrandParent ->
    Parent()
}
val func5: (Parent) -> Parent = { arg: GrandParent ->
    Child()
}

fun acceptCollection(collection: Collection<String>): String {
    return collection.random()
}

fun usingMethod(accept: (List<String>) -> String) {
    accept(listOf())
    accept(mutableListOf())
}

fun main() {
    usingMethod(::acceptCollection)
}

interface UseFamily {
    fun use(arg: Parent): Parent
}

// How can I make a type stronger or weaker?
// stronger: more concrete and possibly with more constraints
val parent: Parent = Parent()
//val child: Child = parent   // the Child is stronger than the Parent
val grandParent: GrandParent = parent   // The GrandParent is weaker than the Parent

// which is stronger List or MutableList?
// which is stronger MutableList or ArrayList?  ArrayList is more concrete => uses an Array which makes it more constrained

class UseFamilyImpl1 : UseFamily {
    override fun use(arg: Parent): Parent {
        return Parent()
    }
}

class UseFamilyImpl2 : UseFamily {
    override fun use(arg: Parent): Parent {
        // how do I make the precondition stronger
        if (arg !is Child) {
            throw IllegalStateException()
        }
        // and this break LSP

        // post-condition => stronger
        return Child()
    }
}

// Kotlin typing does not allow me to define a weaker precondition
//class UseFamilyImpl3 : UseFamily {
//    override fun use(arg: GrandParent): Parent {
//
//    }
//}

// Kotlin typing does allow me to define a strong postcondition
class UseFamilyImpl3 : UseFamily {
    override fun use(arg: Parent): Child {
        // post-condition => stronger
        return Child()
    }
}

fun pay(terminal: IPP350 ) {
    terminal.swiper.swipe()
}

fun pay(terminal: Terminal ) {
    terminal.swiper?.swipe()
}

fun whatever() {
    val useFamily: UseFamily = UseFamilyImpl2()
    val child = Child()
    val child2 = Child2()
    useFamily.use(child2)
}

// weaker: more abstract and possible with fewer constraints


// Interface Segregation Protocol: no code should be forced to depend on methods it does not use

class Swiper {
    fun swipe() {

    }
}

class Tapper {
    fun tap() {

    }
}

class Dipper {
    fun dip() {

    }
}

interface Terminal {
    val swiper: Swiper?
    val tapper: Tapper?
    val dipper: Dipper?
}

interface Swipeable {
    val swiper: Swiper
}

interface Tappable {
    val tapper: Tapper
}

interface Dippable {
    val dipper: Dipper
}

class IPP350 : Swipeable, Dippable {
    // post-condition is stronger (because Swiper <= Swiper?)
    override val swiper: Swiper = Swiper()

    // post-condition is stronger (because Dipper <= Dipper?)
    override val dipper: Dipper = Dipper()
}

class Aries8 : Dippable, Tappable {
    override val tapper: Tapper = Tapper()

    override val dipper: Dipper = Dipper()
}

fun main2() {
    val ipp350 = IPP350()
    val aries8 = Aries8()

    val pos1 = POSSoftware(aries8, aries8)
}

class POSSoftware(private val tappable: Tappable, private val dippable: Dippable) {

    fun payByDipping(amount: Double) {
        dippable.dipper.dip()
    }

    fun payByTapping(amount: Double) {
        tappable.tapper.tap()
    }
}

// Dependency Inversion Protocol: High-level modules should not import anything from low-level modules.
// Both should depend on abstractions

class Dependency: Dependent.MyDependency {
    override fun something() {

    }

    fun somethingElse() {

    }
}

class Dependent(val dependency: MyDependency) {
    interface MyDependency {
        fun something()
    }

    fun whatever() {
        dependency.something()
    }
}

// Abstractions should not depend on details. Details (concrete implementations) should depend on abstractions
// See example in session8 (Console and ConsoleImpl)


// Bibliography:
// https://kotlinlang.org/docs/delegation.html
// https://kotlinlang.org/docs/delegated-properties.html
// https://en.wikipedia.org/wiki/Composition_over_inheritance