package day02

import readInput


fun main() {
    fun part1(input: List<String>): Int {
        val result = input.stream()
            .map { it.split(" ") }
            .map { (a, b) ->
                when (a to b) {
                    "A" to "X" -> 1 + 3
                    "A" to "Y" -> 2 + 6
                    "A" to "Z" -> 3 + 0
                    "B" to "X" -> 1 + 0
                    "B" to "Y" -> 2 + 3
                    "B" to "Z" -> 3 + 6
                    "C" to "X" -> 1 + 6
                    "C" to "Y" -> 2 + 0
                    "C" to "Z" -> 3 + 3
                    else -> error(a to b)
                }
            }.reduce { t, u -> t + u }.orElseThrow()
        return result
    }

    fun part2(input: List<String>): Int {
        val result = input.stream()
            .map { it.split(" ") }
            .map { (a, b) ->
                when (a to b) {
                    "A" to "X" -> 3 + 0
                    "A" to "Y" -> 1 + 3
                    "A" to "Z" -> 2 + 6
                    "B" to "X" -> 1 + 0
                    "B" to "Y" -> 2 + 3
                    "B" to "Z" -> 3 + 6
                    "C" to "X" -> 2 + 0
                    "C" to "Y" -> 3 + 3
                    "C" to "Z" -> 1 + 6
                    else -> error(a to b)
                }
            }.reduce { t, u -> t + u }.orElseThrow()
        return result
    }


    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}

