package day06

import readInput

fun main() {

    fun slidingWindow(input: String, windowSize: Int): Int {
        val window = ArrayDeque<Char>()
        val charArray = input.toCharArray()
        window.addAll(charArray.asSequence().take(windowSize))
        var result = -1

        for (i in windowSize..charArray.size) {
            if (window.toSet().size == windowSize) {
                result = i
                break
            } else {
                window.removeFirst()
                window.addLast(charArray[i])
            }
        }
        return result
    }

    fun part1(input: String): Int {
        return slidingWindow(input, 4)
    }


    fun part2(input: String): Int {
        return slidingWindow(input, 14)
    }

    val testInput = readInput("day06/Day06_test")[0]
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("day06/Day06")[0]
    println(part1(input))
    println(part2(input))
}