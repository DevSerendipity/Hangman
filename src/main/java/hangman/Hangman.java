package hangman;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Hangman {
    private final String word = getRandomWordFromFile();
    private final static Scanner input = new Scanner(System.in);
    private final StringBuilder maskedSentence = new StringBuilder("-".repeat(word.length()));
    private final StringBuilder incorrectCharsEntered = new StringBuilder();
    private final StringBuilder charsEntered = new StringBuilder();

    Hangman() {
        System.out.println(maskedSentence);
        System.out.println(word);
    }

    private String getRandomWordFromFile() {
        Map<Integer, String> words = new HashMap<>();
        try {
            BufferedReader bufferedReader = getBufferedReader();
            addWordsToMap(words, bufferedReader);
        } catch (IOException e) {
            throw new FileSystemNotFoundException("The file could not be found, check the file or adjust the location");
        }
        return words.get((int) (Math.random() * words.size()));
    }

    private void addWordsToMap(Map<Integer, String> words, BufferedReader bufferedReader) throws IOException {
        String line;
        int MAX_LINE = 100;
        for (int i = 0; i < MAX_LINE; i++) {
            line = bufferedReader.readLine().toLowerCase(Locale.ROOT);
            words.put(i, line);
        }
    }

    private BufferedReader getBufferedReader() throws FileNotFoundException {
        File file = new File("src/main/resources/words.txt");
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    public void hangmanGame() {
        int MISTAKE_NUMBER = 7;
        while (incorrectCharsEntered.length() != MISTAKE_NUMBER) {
            String human = input.next();
            charsEntered.append(human);
            characterConditions();
            if (hasWon(human)) break;
            System.out.println("So far " + maskedSentence);
        }
        System.out.println("The word was " + word);
    }

    private void characterConditions() {
        if (!hasCorrectCharactersEntered()) {
            addIncorrectChar(incorrectCharsEntered, charsEntered);
        } else {
            unMaskWord();
        }
        removeCorrectChar(charsEntered);
        getIncorrectChar();
    }

    private void getIncorrectChar() {
        System.out.println("Wrong characters entered " + incorrectCharsEntered);
    }

    private void removeCorrectChar(StringBuilder correctCharsEntered) {
        correctCharsEntered.deleteCharAt(correctCharsEntered.length() - 1);
    }

    private void addIncorrectChar(StringBuilder incorrectCharsEntered, StringBuilder correctCharsEntered) {
        incorrectCharsEntered.append(correctCharsEntered.charAt(correctCharsEntered.length() - 1));
    }

    private void unMaskWord() {
        for (int i = 0; i < word.length(); i++) {
            if (isLetterMatching(i)) {
                maskedSentence.setCharAt(i, charsEntered.charAt(charsEntered.length() - 1));
            }
        }
    }

    private boolean hasWon(String human) {
        if (maskedSentence.toString().equals(word) || human.equals(word)) {
            System.out.println("Congratulations you won");
            return true;
        }
        return false;
    }

    private boolean isLetterMatching(int i) {
        return word.charAt(i) == charsEntered.charAt(charsEntered.length() - 1);
    }

    private boolean hasCorrectCharactersEntered() {
        for (int i = 0; i < word.length(); i++) {
            if (charsEntered.indexOf(String.valueOf(word.charAt(i))) != -1) {
                return true;
            }
        }
        return false;
    }
}