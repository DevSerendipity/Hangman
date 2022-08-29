package hangman;

public class CharacterInput extends WordHandler {

    private final StringBuilder incorrectCharsEntered = new StringBuilder();
    private final StringBuilder charactersEntered = new StringBuilder();

    public StringBuilder getIncorrectCharsEntered() {
        return incorrectCharsEntered;
    }

    public StringBuilder getCharactersEntered() {
        return charactersEntered;
    }

    void unMaskWord() {
        for ( int i = 0; i < getWord().length(); i++ ) {
            if ( isLetterMatching(i) ) {
                getMaskedSentence().setCharAt(i, getCharactersEntered().charAt(getCharactersEntered().length() - 1));
            }
        }
    }

    void characterConditions() {
        if ( !hasCorrectCharactersEntered() ) {
            addIncorrectChar(incorrectCharsEntered, charactersEntered);
        } else {
            unMaskWord();
        }
        removeCorrectChar(charactersEntered);
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
        return getWord().charAt(i) == charactersEntered.charAt(charactersEntered.length() - 1);
    }

    private boolean hasCorrectCharactersEntered() {
        for ( int i = 0; i < getWord().length(); i++ ) {
            if ( charactersEntered.indexOf(String.valueOf(getWord().charAt(i))) != -1 ) {
                return true;
            }
        }
        return false;
    }
}
