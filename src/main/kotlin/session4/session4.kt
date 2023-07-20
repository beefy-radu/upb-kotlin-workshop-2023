package session4

import java.lang.Math.random
import java.util.LinkedList
import java.util.TreeSet

fun main(args: Array<String>) {
    // Arrays
    val intArray: Array<Int> = arrayOf(0, 1, 2, 3)

    // Lists, Sets & Maps. Revisiting mutability.
    // Let's take a look here: https://kotlinlang.org/docs/collections-overview.html#collection-types

    // CRUD = Create Read Update Delete

    // List = sequence of items in a container
    // List options: ArrayList vs LinkedList
    // Create => cate date am acolo?
    // Putine => ArrayList
    // Multe => LinkedList

    // Read? => Iterating => Iterable (Iterator) => Collection (parintele List, Set)
    // Vreau iterare rapida => ArrayList
    // Nu ma intereseaza timpul de iterare => LinkedList

    // Update? => Iterating

    // Delete?
    // ArrayList: sterg elementul shiftand intre array-ul   => Stergeri rare
    // LinkedList: iterez, si refac o legatura              => Stergeri dese
    val arrayList: List<Int> = ArrayList<Int>()
    val linkedList: List<Int> = LinkedList<Int>()

    // Lists use equals to determine .. well.. equality => used in contains
    arrayList.contains(3)

    // What is a Set? => (Translate to RO: Multime (din mate)); no duplicates
    // Sets use hashCode
    // What is a hashing function?
    // Compresses a chunk of data into a Int

    // Let's talk about some implementations? HashSet vs TreeSet
    val set = TreeSet<String>()
    set += "radu"
    set += "fara"
    set += "duplicate"
    set += "intr-o"
    set += "lume"
    set += "nebuna"
    println(set)
    set += "radu"
    println(set)

    // Map => Dictionary with key-value pairs
    // Implementations? HashMap vs TreeMap
    val map = HashMap<String, Int>()

    map += Pair("radu", 35)
    map["radu"] = 35
    println("value = ${map["radu"]}")

    // List & Set implement Collection which implements Iterable
    for (i in linkedList) {

    }

    for (j in set) {

    }

    for ((key, value) in map) {
        println("map[$key]=$value")
    }

    // The five Cs: Create, Convert, Change, Choose, Conclude

    // 1/5 Create
    val myImmutableList = listOf("radu", "are", "mere")
    val myMutableList = mutableListOf("radu", "are", "mere")

    myMutableList[2] = "workshop"
//    myImmutableList[2] = "workshop"   // Does not compile because there are no mutable functions on List

    val myGeneratedList = List(30) {
        it * it
    }

    val myImmutableSet = setOf(1, 2, 3)

    val mapMadeOfEntries = mapOf(
        "radu" to 35,
        "sami" to 20,
        "robert" to 20,
    )
    // 2/5 Convert
    val myConvertedSet = myImmutableList.toSet()

    // 3/5 Change
    val myImmutableListLengths: List<Int> = myImmutableList.map { it.length }

    // 4/5 Choose
    val myFilteredImmutableListLengths: List<Int> = myImmutableListLengths.filter { it > 5 }
    //myImmutableList.filterNot {  }

    // 5/5 Conclude
    myImmutableListLengths.sum()
    myImmutableListLengths.average()
    myImmutableList.any { it.length < 3 }
    myImmutableList.none { it.length > 10 }

    // You can chain them together
    listOf("radu", "are", "mere")       // Create
        .toSet()                        // Convert
        .map { it.length }              // Change
        .filter { it < 3 }              // Choose
        .any { it > 2 }                 // Conclude

    // Folding
    var sum = 0
    for (i in myImmutableListLengths) {
        sum = sum + i
    }
    println("sum = $sum")

    var product = 1
    for (i in myImmutableListLengths) {
        product *= i
    }
    println("product = $product")

    val n = 10
    var factorial = 1
    for (i in (1..n)) {
        factorial *= i
    }
    println("factorial = $factorial")

    fun folding(collection: Collection<Int>, initial: Int, transform: (Int, Int) -> Int): Int {
        var accumulator = initial
        for (i in collection) {
            accumulator = transform(accumulator, i)
        }
        return accumulator
    }

    val sumByFolding = folding(myImmutableListLengths, 0) { acc, i -> acc + i }
    val productByFolding = folding(myImmutableListLengths, initial = 1) { acc, i -> acc * i }

    fun factorial(n: Int): Int {
        if (n <= 0) {
            return 1
        }
        return folding((1..n).toList(), 1) { acc, i -> acc * i }
    }

    val sumByReduce = myImmutableListLengths.reduce { acc, i -> acc + i }
    val productByReduce = myImmutableListLengths.reduce { acc, i -> acc * i }

    // Ranges and progressions
    val range = 1 until 10 step 2
    val range2 = -2..20 step 10
    val rangeInverse = 20 downTo 2 step 3

    for (i in range) {
        println("range1 = $i")
    }
    println()
    for (i in range2) {
        println("range2 = $i")
    }

    println("result for in operator = ${5 in range}")

    // Sequences
    val lotsOfNumbers = List(1_000_000) { random().toInt() }

    lotsOfNumbers
        .asSequence()
        .map { it * 2 }
        .filter { it < 1000 }
        .take(3)
}

// Bibliography
// https://kotlinlang.org/docs/arrays.html
// https://kotlinlang.org/docs/collections-overview.html
// https://medium.com/mobile-app-development-publication/kotlin-collection-functions-cheat-sheet-975371a96c4b
// https://typealias.com/guides/kotlin-sequences-illustrated-guide/