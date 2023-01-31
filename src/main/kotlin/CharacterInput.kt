package hangman

open class CharacterInput : WordHandler() {
    val incorrectCharsEntered = StringBuilder()
    val charactersEntered = StringBuilder()

    private fun unMaskWord() {
        for (i in word.indices) {
            if (isLetterMatching(i)) {
                maskedSentence.setCharAt(i, charactersEntered[charactersEntered.length - 1])
            }
        }
    }

    fun characterConditions() {
        if (!hasCorrectCharactersEntered()) {
            addIncorrectChar(incorrectCharsEntered, charactersEntered)
        } else {
            unMaskWord()
        }
        removeCorrectChar(charactersEntered)
        incorrectChar
    }

    private val incorrectChar: Unit
        get() {
            println("So far wrong characters entered: $incorrectCharsEntered")
        }

    private fun removeCorrectChar(correctCharsEntered: StringBuilder) {
        correctCharsEntered.deleteCharAt(correctCharsEntered.length - 1)
    }

    private fun addIncorrectChar(incorrectCharsEntered: StringBuilder, correctCharsEntered: StringBuilder) {
        incorrectCharsEntered.append(correctCharsEntered[correctCharsEntered.length - 1])
    }

    private fun isLetterMatching(i: Int): Boolean {
        return word[i] == charactersEntered[charactersEntered.length - 1]
    }

    private fun hasCorrectCharactersEntered(): Boolean {
        for (element in word) {
            if (charactersEntered.indexOf(element.toString()) != -1) {
                return true
            }
        }
        return false
    }
}
