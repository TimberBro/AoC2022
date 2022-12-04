package day04

import readInput
import toInt

fun main() {
    fun stringToPairs(it: String): Pair<IntRange, IntRange> {
        val assignments = it.split(",")
        val firstSplit = assignments[0].split("-")
        val secondSplit = assignments[1].split("-")
        return firstSplit[0].toInt()..firstSplit[1].toInt() to secondSplit[0].toInt()..secondSplit[1].toInt()
    }

    fun part1(input: List<String>): Int {
        val result = input.stream()
            .map {
                stringToPairs(it)
            }
            .map {
                it.first.toSet().containsAll(it.second.toSet()) ||
                        it.second.toSet().containsAll(it.first.toSet())
            }.map(Boolean::toInt)
            .reduce { t, u -> t + u }
        return result.orElseThrow()
    }


    fun part2(input: List<String>): Int {
        val result = input.stream()
            .map {
                stringToPairs(it)
            }
            .map {
                it.first.toSet().intersect(it.second.toSet()).isNotEmpty() ||
                        it.second.toSet().intersect(it.first.toSet()).isNotEmpty()
            }.map(Boolean::toInt)
            .reduce { t, u -> t + u }
        return result.orElseThrow()
    }

    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("day04/Day04")
    println(part1(input))
    println(part2(input))
}