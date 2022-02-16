package hangman;

public class CharacterInput extends WordHandler {

    private final String word = getRandomWordFromFile();
    private final StringBuilder maskedSentence = new StringBuilder("-".repeat(word.length()));
    private final StringBuilder incorrectCharsEntered = new StringBuilder();
    private final StringBuilder charsEntered = new StringBuilder();

    public StringBuilder getIncorrectCharsEntered() {
        return incorrectCharsEntered;
    }

    public StringBuilder getCharsEntered() {
        return charsEntered;
    }

    public String getWord() {
        return word;
    }

    public StringBuilder getMaskedSentence() {
        return maskedSentence;
    }

    void unMaskWord() {
        for (int i = 0; i < getWord().length(); i++) {
            if (isLetterMatching(i)) {
                getMaskedSentence().setCharAt(i, getCharsEntered().charAt(getCharsEntered().length() - 1));
            }
        }
    }

    void characterConditions() {
        if (!hasCorrectCharactersEntered()) {
            addIncorrectChar(incorrectCharsEntered, charsEntered);
        } else {
            unMaskWord();
        }
        removeCorrectChar(charsEntered);
        getIncorrectChar();
    }

    private void getIncorrectChar() {
        System.out.println("So far wrong characters entered: " + incorrectCharsEntered);
    }

    private void removeCorrectChar(StringBuilder correctCharsEntered) {
        correctCharsEntered.deleteCharAt(correctCharsEntered.length() - 1);
    }

    private void addIncorrectChar(StringBuilder incorrectCharsEntered, StringBuilder correctCharsEntered) {
        incorrectCharsEntered.append(correctCharsEntered.charAt(correctCharsEntered.length() - 1));
    }

    boolean isLetterMatching(int i) {
        return getWord().charAt(i) == charsEntered.charAt(charsEntered.length() - 1);
    }

    private boolean hasCorrectCharactersEntered() {
        for (int i = 0; i < getWord().length(); i++) {
            if (charsEntered.indexOf(String.valueOf(getWord().charAt(i))) != -1) {
                return true;
            }
        }
        return false;
    }
}
