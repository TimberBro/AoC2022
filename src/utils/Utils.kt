import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun separateInputByEmptyLine(input: List<String>): List<List<String>> {
    val entitiesList = ArrayList<List<String>>()
    var entity = ArrayList<String>()
    val iterator = input.listIterator()
    while (iterator.hasNext()) {
        val line = iterator.next()
        if (line.isNotEmpty()) {
            entity.add(line)
        } else if (line.isEmpty()) {
            entitiesList.add(entity)
            entity = ArrayList()
        }
    }
    entitiesList.add(entity)
    return entitiesList
}