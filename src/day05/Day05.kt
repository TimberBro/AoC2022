package day05

import readInput

data class Instruction(val quantity: Int, val source: Int, val destination: Int)

fun main() {
    fun part1(input: List<String>): String {
        val crates = parseCrates(input)
        val instructions = parseInstructions(input)

        for (instruction in instructions) {
            for (i in 1..instruction.quantity)
                crates[instruction.destination].addLast(crates[instruction.source].removeLast())
        }

        val result = StringBuilder()
        for (crate in crates) {
            result.append(crate.removeLast())
        }

        return result.toString()
    }

    fun part2(input: List<String>): String {
        val crates = parseCrates(input)
        val instructions = parseInstructions(input)

        for (instruction in instructions) {
            if (instruction.quantity == 1) {
                crates[instruction.destination].addLast(crates[instruction.source].removeLast())
            } else {
                val buffer = ArrayList<Char>()
                for (i in 1..instruction.quantity) {
                    buffer.add(crates[instruction.source].removeLast())
                }
                crates[instruction.destination].addAll(buffer.reversed())
            }
        }

        val result = StringBuilder()
        for (crate in crates) {
            result.append(crate.removeLast())
        }
        return result.toString()
    }

    val testInput = readInput("day05/Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("day05/Day05")
    println(part1(input))
    println(part2(input))
}

private fun parseCrates(input: List<String>): ArrayList<ArrayDeque<Char>> {
    val cratesMap = input.asSequence()
        .flatMap { "\\[(\\w+)\\]".toRegex().findAll(it) }
        .groupBy { it.range.first + 1 }
        .toMap()

    // Parse second time to work with indexes as usual
    val crates = ArrayList<ArrayDeque<Char>>()
    for (entry in cratesMap.entries.sortedBy { it.key }) {
        val line = ArrayDeque<Char>()
        for (result in entry.value) {
            line.addFirst(result.groupValues[1].toCharArray()[0])
        }
        crates.add(line)
    }
    return crates
}

private fun parseInstructions(input: List<String>): List<Instruction> {
    val instructions = input.asSequence()
        .flatMap { "move (\\d+) from (\\d+) to (\\d+)".toRegex().findAll(it) }
        .map { x ->
            Instruction(
                x.groupValues[1].toInt(),
                x.groupValues[2].toInt() - 1,
                x.groupValues[3].toInt() - 1
            )
        }
        .toList()
    return instructions
}