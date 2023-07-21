package session6

fun String.capitalize(): String {
    return replaceFirstChar { it.uppercaseChar() }
}