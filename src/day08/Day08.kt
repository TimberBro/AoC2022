package day08

import readInput

fun main() {

    fun outerLine(parsedInput: List<List<Int>>, row: Int, column: Int): Boolean {
        return (row == 0 || column == 0 || row == parsedInput[0].size - 1 || column == parsedInput.size - 1)
    }

    fun visibleFromLeft(parsedInput: List<List<Int>>, row: Int, column: Int): Boolean {
        val line = parsedInput[row]
        val value = line[column]
        for (i in 0 until column) {
            if (line[i] >= value) {
                return false
            }
        }
        return true
    }

    fun visibleFromRight(parsedInput: List<List<Int>>, row: Int, column: Int): Boolean {
        val line = parsedInput[row]
        val value = line[column]
        for (i in column + 1 until line.size) {
            if (line[i] >= value) {
                return false
            }
        }
        return true
    }

    fun visibleFromTop(parsedInput: List<List<Int>>, row: Int, column: Int): Boolean {
        val line = parsedInput[row]
        val value = line[column]
        for (i in 0 until row) {
            if (parsedInput[i][column] >= value) {
                return false
            }
        }
        return true
    }

    fun visibleFromBottom(parsedInput: List<List<Int>>, row: Int, column: Int): Boolean {
        val line = parsedInput[row]
        val value = line[column]
        for (i in row + 1 until parsedInput.size) {
            if (parsedInput[i][column] >= value) {
                return false
            }
        }
        return true
    }

    fun countToLeft(parsedInput: List<List<Int>>, row: Int, column: Int): Int {
        val line = parsedInput[row]
        val value = line[column]
        var result = 0
        for (i in column - 1 downTo 0) {
            if (line[i] < value) {
                result++
            } else {
                return ++result
            }
        }
        return result
    }

    fun countToRight(parsedInput: List<List<Int>>, row: Int, column: Int): Int {
        val line = parsedInput[row]
        val value = line[column]
        var result = 0
        for (i in column + 1 until line.size) {
            if (line[i] < value) {
                result++
            } else {
                return ++result
            }
        }
        return result
    }

    fun countToTop(parsedInput: List<List<Int>>, row: Int, column: Int): Int {
        val line = parsedInput[row]
        val value = line[column]
        var result = 0
        for (i in row - 1 downTo 0) {
            if (parsedInput[i][column] < value) {
                result++
            } else {
                return ++result
            }
        }
        return result
    }

    fun countToBottom(parsedInput: List<List<Int>>, row: Int, column: Int): Int {
        val line = parsedInput[row]
        val value = line[column]
        var result = 0
        for (i in row + 1 until parsedInput.size) {
            if (parsedInput[i][column] < value) {
                result++
            } else {
                return ++result
            }
        }
        return result
    }

    fun countScore(parsedInput: List<List<Int>>, row: Int, column: Int): Int {
        val result = 1
        val left = countToLeft(parsedInput, row, column)
        val right = countToRight(parsedInput, row, column)
        val top = countToTop(parsedInput, row, column)
        val bottom = countToBottom(parsedInput, row, column)

        return result * left * right * top * bottom

    }

    fun part1(input: List<String>): Int {
        var result = 0
        val parsedInput = input.asSequence()
            .map { it.toCharArray() }
            .map { it.asSequence().map { it.digitToInt() } }
            .map { it.toList() }
            .toList()
        for ((rowIndex, row) in parsedInput.withIndex()) {
            for ((columnIndex, _) in row.withIndex()) {
                if (outerLine(parsedInput, rowIndex, columnIndex) ||
                    visibleFromBottom(parsedInput, rowIndex, columnIndex) ||
                    visibleFromTop(parsedInput, rowIndex, columnIndex) ||
                    visibleFromLeft(parsedInput, rowIndex, columnIndex) ||
                    visibleFromRight(parsedInput, rowIndex, columnIndex)
                ) {
                    result++
                }
            }
        }

        return result
    }


    fun part2(input: List<String>): Int {
        val parsedInput = input.asSequence()
            .map { it.toCharArray() }
            .map { it.asSequence().map { it.digitToInt() } }
            .map { it.toList() }
            .toList()
        val scores = ArrayList<Int>()
        countScore(parsedInput, 3, 2)
        for ((rowIndex, row) in parsedInput.withIndex()) {
            for ((columnIndex, _) in row.withIndex()) {
                scores.add(countScore(parsedInput, rowIndex, columnIndex))
            }
        }
        return scores.max()
    }

    val testInput = readInput("day08/Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("day08/Day08")
    println(part1(input))
    println(part2(input))
}