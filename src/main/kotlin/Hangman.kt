package hangman

import java.util.*

class HangmanJava : CharacterInput() {
    init {
        println(maskedSentence)
    }

    fun game() {
        val numberOfMistakes = 7
        while (incorrectCharsEntered.length != numberOfMistakes) {
            val human = readln()
            charactersEntered.append(human)
            characterConditions()
            if (hasWon(human)) {
                break
            }
            println("So far $maskedSentence")
        }
        println("The word was $word")
    }

    private fun hasWon(human: String): Boolean {
        if (maskedSentence.toString() == word || human == word) {
            println("Congratulations you won!!")
            return true
        }
        return false
    }

}
