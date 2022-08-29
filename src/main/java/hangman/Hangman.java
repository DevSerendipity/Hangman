package hangman;

import java.util.Scanner;

public class Hangman extends CharacterInput {

    private final static Scanner input = new Scanner(System.in);

    Hangman() {
        System.out.println(getMaskedSentence());
    }

    public void game() {
        int NUMBER_OF_MISTAKES = 7;
        while( getIncorrectCharsEntered().length() != NUMBER_OF_MISTAKES ) {
            String human = input.next();
            getCharactersEntered().append(human);
            characterConditions();
            if ( hasWon(human) ) {
                break;
            }
            System.out.println("So far " + getMaskedSentence());
        }
        System.out.println("The word was " + getWord());
    }

    private boolean hasWon(String human) {
        if ( getMaskedSentence().toString().equals(getWord()) || human.equals(getWord()) ) {
            System.out.println("Congratulations you won!!");
            return true;
        }
        return false;
    }

}
