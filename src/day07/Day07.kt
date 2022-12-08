package day07

import readInput

data class File(val name: String, val size: Int)

class Directory(private val name: String) {

    var parentDirectory: Directory? = null
    val files = ArrayList<File>()
    var directories = ArrayList<Directory>()

    fun addChild(directory: Directory) {
        directories.add(directory)
        directory.parentDirectory = this
    }

    fun calculateSize(): Int {
        return files
            .asSequence()
            .map { it.size }
            .sum() +
                directories
                    .asSequence()
                    .map { it.calculateSize() }
                    .sum()
    }

    override fun toString(): String {
        return name
    }

    fun findIndexByName(name: String): Int {
        var result = -1
        for (i in 0..directories.size) {
            val currentDirectory = directories[i]
            if (currentDirectory.name == name) {
                result = i
                break
            }
        }
        return result
    }
}

fun main() {

    fun directoriesSet(rootDirectory: Directory, set: HashSet<Directory>) {
        rootDirectory.calculateSize()
        set.add(rootDirectory)
        set.addAll(rootDirectory.directories)
        for (directory in rootDirectory.directories) {
            directory.calculateSize()
            directoriesSet(directory, set)
        }
    }

    fun parseFileSystem(input: List<String>): Directory {
        val rootDirectory = Directory("/")
        var currentDirectory = rootDirectory
        val foldersRegex = "dir (\\w+)".toRegex()
        val commandRegex = "\\\$ cd (\\w+)".toRegex()
        val filesRegex = "\\d+.+".toRegex()
        val moveBackRegex = "\\\$ cd ..".toRegex()
        input.asSequence()
            //.onEach { println(it) }
            .map {
                when {
                    it.matches(foldersRegex) -> currentDirectory.addChild(Directory(it.split(" ")[1]))
                    it.matches(filesRegex) -> currentDirectory.files.add(
                        File(
                            it.split(" ")[1],
                            it.split(" ")[0].toInt()
                        )
                    )
                    it.matches(commandRegex) -> currentDirectory =
                        currentDirectory.directories[currentDirectory.findIndexByName(it.split(" ")[2])]
                    it.matches(moveBackRegex) -> currentDirectory =
                        currentDirectory.parentDirectory!!
                    else -> {}
                }
            }.count()
        return rootDirectory
    }

    fun part1(input: List<String>): Int {
        val rootDirectory = parseFileSystem(input)
        val allDirectories = HashSet<Directory>()
        directoriesSet(rootDirectory, allDirectories)
        return allDirectories.asSequence()
            .map { it.calculateSize() }
            .filter { it <= 100000 }
            .sum()
    }


    fun part2(input: List<String>): Int {
        val rootDirectory = parseFileSystem(input)
        val allDirectories = HashSet<Directory>()
        directoriesSet(rootDirectory, allDirectories)

        val unusedSpace = 70000000 - rootDirectory.calculateSize()
        val requiredSpace = 30000000 - unusedSpace
        return allDirectories.asSequence()
            .map { it.calculateSize() }
            .filter { it > requiredSpace }
            .min()
    }


    val testInput = readInput("day07/Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("day07/Day07")
    println(part1(input))
    println(part2(input))
}
