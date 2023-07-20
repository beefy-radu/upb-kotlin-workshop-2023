package session3

import java.time.Duration
import java.util.concurrent.TimeUnit

typealias Callback = () -> Unit

fun timer(timeUnit: TimeUnit = TimeUnit.SECONDS, block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()
    val duration = Duration.ofMillis(end - start)
    val interval = timeUnit.convert(duration)
    println("It took me: $interval")
    return interval
}

fun main(args: Array<String>) {
    // Functions and scopes
    fun myFunction(arg1: String, arg2: Int): Int {
        return arg1.toInt() + arg2
    }

    fun returnsUnit() {
        // does not require specific return type
    }

    fun neverEndingStory(): Nothing {
        while (true) {

        }
    }

    fun alwaysFails(): Nothing {
        throw IllegalAccessError()
    }
    // Say goodbye to all the overloads and say hello to default arguments
    fun defaultFunctionArguments(arg1: String = "Default", arg2: Int = 2, arg3: Float = 3.14f,) {
        println("arg1=$arg1 arg2=$arg2 arg3=$arg3")
    }

    defaultFunctionArguments()
    defaultFunctionArguments("Custom String")
    defaultFunctionArguments("Custom String", 42)
    defaultFunctionArguments("Custom String", 42, 4.13f)

    // Call me by your name
    defaultFunctionArguments(arg3 = 3.58f)

    fun singleDefaultFunctionArgument(arg1: String = "Default") {

    }

    singleDefaultFunctionArgument(arg1 = "Custom String")

    // vararg
    fun varargFun(anotherArg: String, vararg args: String) {
        for (arg in args) {
            println("arg=$arg")
        }
    }

    varargFun("Radu", "Nu", "Stie", "ce se intampla")

    // Single expression functions
    fun double(x: Int): Int {
        return x * 2
    }

    fun doubleSingleExpression(x: Int) = x * 2

    fun whenSingleExpression(x: Int) = when {
        x < 0 -> x * 2
        x > 0 -> x * 4
        else -> 0
    }

    // Be vewy vewy caweful
    fun doThis(x: Int): Unit { x * 2 } // => Return type: Unit
    fun dontDoThis(x: Int): () -> Int = { x * 2 }   // => Return type: ???

    // Functional type: (T1, T2, T3, ... etc) -> R
    val whatIs26 = dontDoThis(13)

    whatIs26.invoke() // whatIs26()

    println(whatIs26())
    println(whatIs26())
    println(whatIs26())
    println(whatIs26())

    // Typing revisited, sometimes with anonymity
    val myFun = fun (s: String): Int {
        return s.length * 2
    }

    // Lambdas & higher order functions
    val myLambda: (String, Int) -> Int = { s: String, arg2: Int ->
        println("$s $arg2")
        val innerScopeVariable = 2
        s.length + arg2
    }

    val myCallback = {
        println("Un mesaj")
    }


    fun higherOrderFunction(arg1: Int, callback: Callback) {
        if (arg1 > 0) {
            callback()
        } else {
            println("number is negative")
        }
    }

    higherOrderFunction(32, myCallback)

    // Follow the trail(ing lambda)

    fun anotherHigherOrderFunction(arg1: String, arg2: Int, mapToInt: (String) -> Int): Int {
        return mapToInt(arg1) + arg2
    }

    anotherHigherOrderFunction("3", 2) { s: String ->
        s.toInt()
    }

    fun singleArgHigherOrderFunction(arg: () -> Unit) {

    }

    singleArgHigherOrderFunction { println("Exist") }

    timer {
        var sum = 0
        for (i in 1..1000000) {
            sum += i
        }
    }

    timer(timeUnit = TimeUnit.MICROSECONDS) {
        Thread.sleep(1000)
    }

    // Returns and jumps
    (1..1000).forEach loop1@{
        (2..2000).forEach loop2@{
            println(it)
        }
    }

    // Have you seen the movie "it"?
    // It's ok to do this
    (1..1000).forEach {
        println("Just use $it as 'it'")
    }

    // This is not really ok
    (1..1000).forEach { i: Int ->
        (2..2000).forEach { j: Int ->
            // For the love of God please name it
            println("Much better i=$i j=$j")
        }
    }

    // What if I don't care?
    fun multipleArgumentsInCallback(callback: (String, Int, Double) -> Unit) {

    }
    // _ => i dooooon't caaaare
    multipleArgumentsInCallback { _, arg2: Int, _ ->
        println("i'm only using arg2: $arg2")
    }

    // vararg not supported in lambda definitions
    // This does not compile
//    fun varargHigherOrderFunction(block: (vararg String) -> Unit) {
//
//    }

    // Tip functional se specifica: (T1, T2, ... etc) -> R
}

// Bibliography
// https://kotlinlang.org/docs/functions.html
// https://kotlinlang.org/docs/lambdas.html
// https://kotlinlang.org/docs/returns.html