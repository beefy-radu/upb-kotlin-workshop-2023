package session2

fun main(args: Array<String>) {
    println("Hello World!")

    // Variable declaration. There can be only two...
    val myValue: Int = 1   // final-ul din Java
    var myVariable: Float = 24f // variabilele din Java

    // Typing and inference
    val mySecondValue = 5
    var mySecondVariable = 24f

    // Numbers: Long, Integers, Short, Byte, Floating Point (S&P)
    mySecondVariable = mySecondValue.toFloat()

    var myDivision = mySecondValue.toFloat() / myValue.toFloat()

    // Experimental: Unsigned
    // Booleans are not numbers
    val myBoolean: Boolean = (myValue == 1)

    // Strings & Characters
    val myString = "Hello, World ${String.format("%3f", mySecondVariable)}}"

    val whatever = "my string" + 5 + 10
    println(whatever)

    // Nullability and null safety. Elvis has entered the building
    val myNonNullable: Int = 0
    val myNullable: Int? = args.getOrNull(0)?.toInt()

    myNonNullable.toFloat().compareTo(2).toFloat()
    val result: Float? = myNullable?.toFloat()?.compareTo(2)?.toFloat()

    // Conditionals
    when {
        myNonNullable == 0 -> {

        }
        myNonNullable > 0 -> {

        }
        myNonNullable < 0 -> {

        }
    }

    when (myNonNullable) {
        0 -> {

        }
        1 -> {

        }
        2 -> {

        }
        else -> {

        }
    }

    when {
        myNonNullable in 1..8 -> {

        }
        myNonNullable in -10..0 -> {

        }
    }


    // Loops
    for (i in 0..myNonNullable) {

    }

//    while (true)
//        ;


//    do {
//
//    } while()



    // Exceptions
//    try {
//        throw IllegalAccessError()
//    } catch() {
//
//    } catch() {
//
//    } finally {
//
//    }

    // Expressions
    val myCondition = if (myNonNullable != null) {
        12
    } else {
        14
    }

    val result2 = when (myNonNullable) {
        0 -> {
            "this is 0"
        }
        1 -> {
            "hey, it at least have one"
        }
        2 -> {
            "two's a couple"
        }
        else -> {
            "i don't know how to "
        }
    }

    val myFancyResult = try {
        "2.45ffg".toFloat()
    } catch (exception: Exception) {
        13f
    }
}