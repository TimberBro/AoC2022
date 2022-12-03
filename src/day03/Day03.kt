package day03

import readInput
import separateInputByRows

fun main() {
    fun part1(input: List<String>): Int {
        val result = input.stream()
            .map { it.take(it.length / 2) to it.takeLast(it.length / 2) }
            .map { it.first.toCharArray().intersect(it.second.asIterable().toSet()).first() }
            .map {
                when (it) {
                    in 'a'..'z' -> it.code - 96
                    in 'A'..'Z' -> it.code - 38
                    else -> error("Could not transform")
                }
            }.reduce { t, u -> t + u }
        return result.orElseThrow()
    }

    fun part2(input: List<String>): Int {
        val rowsGroups = separateInputByRows(input, 3)
        val result = rowsGroups.stream()
            // explicitly intersect all three rows
            .map { (x, y, z) ->
                x
                    .toCharArray()
                    .intersect(y.asIterable().toSet())
                    .intersect(z.asIterable().toSet())
                    .first()
            }
            .map {
                when (it) {
                    in 'a'..'z' -> it.code - 96
                    in 'A'..'Z' -> it.code - 38
                    else -> error("Could not transform")
                }
            }.reduce { t, u -> t + u }

        return result.orElseThrow()
    }

    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}