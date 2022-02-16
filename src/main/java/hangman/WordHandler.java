package hangman;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WordHandler {

    String getRandomWordFromFile() {
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
}
