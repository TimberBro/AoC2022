package day01

import readInput
import separateInputByEmptyLine
import kotlin.streams.toList

fun main() {


    fun part1(input: List<String>): Int {
        val inputByEmptyLine = separateInputByEmptyLine(input)
        val maxValue = inputByEmptyLine
            .stream()
            .map {
                it.stream().mapToInt(String::toInt).sum()
            }
            .toList()
            .max()
        return maxValue
    }

    fun part2(input: List<String>): Int {
        val inputByEmptyLine = separateInputByEmptyLine(input)
        val sumTopThree = inputByEmptyLine
            .asSequence()
            .map {
                it.stream().mapToInt(String::toInt).sum()
            }
            .sortedDescending()
            .take(3)
            .sum()
        return sumTopThree
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("day01/Day01")
    println(part1(input))
    println(part2(input))
}
