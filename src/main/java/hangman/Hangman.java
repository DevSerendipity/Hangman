package hangman;

import java.util.Scanner;

public class Hangman extends CharacterInput {

    private final static Scanner input = new Scanner(System.in);

    Hangman() {
        System.out.println(getMaskedSentence());
        System.out.println(getWord());
    }

    public void hangmanGame() {
        int MISTAKE_NUMBER = 7;
        while (getIncorrectCharsEntered().length() != MISTAKE_NUMBER) {
            String human = input.next();
            getCharsEntered().append(human);
            characterConditions();
            if (hasWon(human)) break;
            System.out.println("So far " + getMaskedSentence());
        }
        System.out.println("The word was " + getWord());
    }

    private boolean hasWon(String human) {
        if (getMaskedSentence().toString().equals(getWord()) || human.equals(getWord())) {
            System.out.println("Congratulations you won!!");
            return true;
        }
        return false;
    }

}