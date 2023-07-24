package session7

import java.util.Date

sealed class Entry {
    abstract fun display()
}

// O functionalitate care printeaza o lista
// Headere => ##### title #####
// Iteme => * item, published at time

data class Header(val title: String) : Entry() {
    override fun display() {
        println("##### $title #####")
    }
}

data class Item(val item: String, val time: Date) : Entry() {
    override fun display() {
        println("* $item, published at $time")
    }
}

object Breakline : Entry() {
    override fun display() {
        println("------------")
    }
}

fun listEntries(entries: List<Entry>) {
    var countHeaders = 0
    for (entry in entries) {
        entry.display()

        when (entry) {
            is Header -> countHeaders++
            is Item -> { }
            Breakline -> { }
        }
    }
}

fun main() {
    listEntries(
        listOf(
            Header("Today"),
            Item("Finish workshop session #1", Date()),
            Item("Finish workshop session #2", Date()),
            Item("Finish workshop session #3", Date()),
            Header("Tomorrow"),
            Item("Finish workshop session #4", Date()),
            Item("Finish workshop session #5", Date()),
            Item("Finish workshop session #6", Date()),
        )
    )
}