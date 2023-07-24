package session6

import session7.Entry
import java.util.*

// Enum Classes
enum class Direction {
    North,
    South,
    East,
    West;

    // Enums can have properties
    val myProp: String = "Whatever"

    // Enums can have methods
    fun whatever() {

    }
}

// Object declarations
// What is a singleton?
class Singleton private constructor() {
    companion object {
        val instance = Singleton()
    }
}
// What happened to static?
object MySingleton {
    val myList = mutableListOf<String>()
    var nullable: String? = null
    lateinit var nonNullable: String

    fun whatever() {

    }
}

fun main(args: Array<String>) {
    // Classes
    class Person {
        val myImmutableProperty: String = "Radu"
        var myMutableProperty: Int = 42
    }

    // Properties
    val person = Person()

    println(person.myImmutableProperty)
//    person.myImmutableProperty = "Radu"
    person.myMutableProperty = 43

    // Constructors
    // Init blocks
    class Catalog(name: String, yearOfBirth: Int) {
        val name: String = name

        constructor(person: Person) : this(person.myImmutableProperty, person.myMutableProperty) {
            println("constructor")
        }

        val age: Int
        init {
            println("init block1")
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            age = calendar.get(Calendar.YEAR) - yearOfBirth
        }

        init {
            println("init block2")
        }

        init {
            println("init block3")
        }

        val whatever: String = "whatevs"
    }
    Catalog(person)
    // When creating an object => first all properties & init blocks are executed, and the constructor at the end

    // Access modifiers
    // Java? private, public, protected, package-private (default is package-private)
    // Kotlin? private, public, protected, internal (default is public)
    class WithAccessModifiers {
        private val x: Int = 0
    }

    // Defining properties inside of the default constructor
    class Car(
        private val brand: String,
        private val fuelType: String,
        val consumption: Float,
        name: String
    ) {
        private val otherProperty: Double = 0.3
    }

    // a simplified version of this
    class ComplexCar(brand: String, fuelType: String, consumption: Float, name: String) {
        private val brand: String = brand
        private val fuelType: String = fuelType
        val consumption: Float = consumption

        private val otherProperty: Double = 0.3
    }

    val car = Car("Mercedes", "Electric", 12.3f, "MyCar")
    var complexCar = ComplexCar("Mercedes", "Electric", 12.3f, "MyCar")

    // Encapsulation
    // val => valoare + getter
    // var => variabila + getter + setter
    class Encapsulation {
        val x: Int = 0
            get() = field

        val time: Long
            get() = System.currentTimeMillis()

        var radians: Double = 0.0
            get() = Math.toRadians(field)
            set(value) {
                field = Math.toDegrees(value)
            }

        var envVariable: String
            get() = System.getenv("path") ?: "default"
            set(value) {
                System.getenv().set("path", value)
            }
    }

    // Java translation
//    class JavaEncapsulation {
////        val x: Int = 0
////            get() = field
//        private Integer x = 0;
//
//        Integer getX() {
//            return x;
//        }
//
////        val time: Long
////            get() = System.currentTimeMillis()
//        Long getTime() {
//            return System.currentTimeMillis();
//        }
//
////        var radians: Double = 0.0
////            get() = Math.toRadians(field)
////            set(value) {
////                field = Math.toDegrees(value)
////            }
//        private Double degrees = 0.0;
//
//        public Double getRadians() {
//            return Math.toRadians(degrees);
//        }
//
//        public void setRadians(Double value) {
//            degrees = Math.toDegrees(value)
//        }
//
////        var envVariable: String
////            get() = System.getenv("path")
////            set(value) {
////                System.getenv().set("path", value)
////            }
//
//        String getEnvVariable() {
//            return System.getenv("path");
//        }
//
//        void setEnvVariable(String value) {
//            System.getenv().set("path", value)
//        }
//    }

    val enc = Encapsulation()
    println("xxx ${enc.envVariable}")
//    enc.envVariable = "My new path"

    // Encapsulation pattern 1: private setter
    class PrivateSetter {
        var modifiableFromInside: String = "arbitrary"
            private set

        fun whatever() {
            modifiableFromInside = "non-arbitrary"
        }
    }

    val instance1 = PrivateSetter()
//    instance1.modifiableFromInside = "from-the-outside"
    instance1.whatever()

    // Encapsulation pattern 2: backing property
    class PrivateSetterForCollection {
        private val _myList: MutableList<String> = mutableListOf()
        val myList: List<String>
            get() = _myList

        fun collect(value: String) {
            _myList += value
        }
    }

    val listPattern = PrivateSetterForCollection()
//    listPattern.myList += "radu"  // Cannot change it from the outside
    listPattern.collect("radu")

    // Late init
    class Mentor(val name: String) {
        fun teach() {
            println("$name is teaching")
        }

        fun grade() {
            println("$name is grading")
        }
    }

    class Subject(val topic: String) {
        private lateinit var mentor: Mentor

        fun start(mentor: Mentor) {
            this.mentor = mentor
            mentor.teach()
        }

        fun stop() {
            mentor.grade()
        }
    }

    class School {
        private val subjects = listOf<Subject>(
            Subject("PC"),
            Subject("POO")
        )

        fun start(mentors: List<Mentor>) {
               for (subject in subjects) {
                    subject.start(mentors.random())
               }
        }

        fun stop() {
            for (subject in subjects) {
                subject.stop()
            }
        }
    }

    School().start(listOf(Mentor("Ionut")))

    // Methods
    class ClassWithMethods(val x: String) {
        fun method() {
            // How many arguments does this have?
            // "this" is always a hidden first argument to every instance method
            println("xxx $x")
        }
    }
    // OOP => Objects are called receivers and method calls imply sending a message to a receiver
    val receiver = ClassWithMethods("whatever")
    receiver.method()   // i'm sending the "method" message to the "receiver"

    val referenceToAClassMethod: (ClassWithMethods) -> Unit = ClassWithMethods::method
    val referenceToAmInstanceMethod: () -> Unit = receiver::method

    referenceToAClassMethod(receiver)
    referenceToAmInstanceMethod()

    // Data Classes
    data class Dog(
        val name: String,
        val breed: String,
        val age: Int,
    ) {
        val other: Double = 0.0 // Not accounted for by the data class code generation

        fun compute() {

        }
    }

    // Automatically generates: equals (List.contains), hashCode (Set => unicitate), components, toString
    val dog = Dog("Milo", "Golden Retriever", 12)
    val (name, _, age) = dog

    val geronimo = dog.copy(name = "Geronimo")

    println("Milo = $dog")
    println("Geronimo = $geronimo")

    // Extensions
    // See IntExt.kt and StringExt.kt

    if (12.isEven()) {
        println("12 is even")
    }

    // String is final and we can't extend it to add functionality
    fun aNewScope() {
        // Creating an extension is only visible in the same scope
        fun String.scoped() {
            println("This is in this scope")
        }

        "Inside of scope".scoped()
    }

//    "Outside of Scope".scoped() // Does not compile
    aNewScope()
}

// eye-candy - sugar-code
// extensions functions are useful for final types => types that cannot be extended
// In Java we don't have String.capitalize()
// So, what did we in Java before Kotlin added extension functions?
//public static String capitalize(String string) {
//
//}
//
//"radu".capitalize() <=> capitalize("radu")
//fun String.capitalize()


// Bibliography
// https://kotlinlang.org/docs/classes.html (up until Inheritance)
// https://kotlinlang.org/docs/properties.html
// https://kotlinlang.org/docs/visibility-modifiers.html
// https://kotlinlang.org/docs/data-classes.html
// https://kotlinlang.org/docs/enum-classes.html
// https://kotlinlang.org/docs/object-declarations.html
// https://kotlinlang.org/docs/extensions.html