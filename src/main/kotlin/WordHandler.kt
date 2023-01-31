package hangman

import java.io.*
import java.nio.file.FileSystemNotFoundException

open class WordHandler {
    val word = randomWord
    val maskedSentence = StringBuilder("-".repeat(word.length))
    private val randomWord: String
        get() {
            val words = arrayListOf<String>()
            try {
                readFileAsLinesUsingUseLines(words)
            } catch (e: IOException) {
                throw FileSystemNotFoundException("The file could not be found, check the file or adjust the location")
            }
            return words[(Math.random() * words.size).toInt()]
        }

    private fun readFileAsLinesUsingUseLines(words: ArrayList<String>)
            = File("src/hangman.main/resources/words.txt").forEachLine {words.add(it) }
}
