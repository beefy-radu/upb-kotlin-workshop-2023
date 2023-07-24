package session7

import kotlin.math.abs

// Inheritance => Reusability => DRY (Don't Repeat Yourself)
// Two things we can achieve by inheritance: Specialize vs Extend

// List vs MutableList, who's the parent => List => Excellent example of Extend => adding new behavior
// What could Specialize mean? MutableList vs ArrayList => overriding behavior

// In Kotlin, everything Type is final, except if we describe it otherwise
open class Base

class Derived : Base(), Interface

// How many classes and how many interfaces can one class inherit? => Only one class, any number of interfaces
//          A   --> equals
//         / \
//        /   \
//        B    C    --> equals
//        \   /
//         \ /
//          D   --> equals gets called here? Which one is it?

// Through inheritance we get: public behavior

open class Base2(x: Int, val y: Double) {
    constructor(x: Int) : this(x, 0.0)

    constructor() : this(0, 0.0)

    open fun method() {
        println("This is the base class")
    }
}

class Derived2(x : Int, y: Double) : Base2(x, y) {
    private val z: Int = 0

    constructor(x : Int) : this(x, 0.0)

    constructor() : this(0, 0.0)

    override fun method() {
        println("This is the child class")
    }

    fun method2() {
        println("whatever")
    }
}

// Interfaces & Abstract Classes
interface Interface {
//    val propImmutable: String
//    var propMutable: String
//
//    fun method()
}

abstract class MyAbstractClass(val existing: String) {
    abstract val prop: Int

    var number: Float = 0f

    fun method() {
        println("This is a method")
    }

    abstract fun abstractMethod()
}

// Sealed Classes
// See Entry.kt

fun main() {
    // Polymorphism
    val base: Base2 = Base2()
    base.method()   // This is the base class

    val derived: Derived2 = Derived2()
    derived.method()    // This is the child class
    derived.method2()

    val base2: Base2 = Derived2()
    base2.method()  // This is the base class? No => This is the child class

    abstract class Animal {
        abstract fun eat()

        open fun display() {
            println("I'm an animal")
        }

        fun live() {
            while (true) {
                eat()
                move()
                sleep()
            }
        }

        fun move() {
            println("I'm moving")
        }

        fun sleep() {
            println("I'm sleeping")
        }
    }

    class Dog : Animal() {
        override fun eat() {
            println("I eat dog good")
        }

        override fun display() {
            println("I'm a dog")
        }
    }

    class Cat : Animal() {
        override fun eat() {
            println("I eat cat good")
        }

        override fun display() {
            println("I'm a cat")
        }
    }

    class Mouse : Animal() {
        override fun eat() {
            println("I'm eating mouse food")
        }

        override fun display() {
            println("I'm a mouse")
        }
    }

    class Moose : Animal() {
        override fun eat() {
            TODO("Not yet implemented")
        }
    }

    val list: List<Animal> = listOf(
        Cat(),
        Dog()
    )

    for (animal in list) {
        animal.eat()
        animal.display()

        val x = when (animal) {
            is Cat -> { println("I found a cat") }
            is Dog -> { println("I found a dog") }
            is Mouse -> {
                println("I found a mouse")
            }
            else -> {
                println("I found something else")
            }
        }
    }
}

// Bibliography
// https://kotlinlang.org/docs/inheritance.html
// https://kotlinlang.org/docs/sealed-classes.html
// https://en.wikipedia.org/wiki/Multiple_inheritance#The_diamond_problem