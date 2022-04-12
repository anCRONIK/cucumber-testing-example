package net.ancronik.samples.cucumber.acceptance;

import net.ancronik.samples.cucumber.data.model.Author;
import net.ancronik.samples.cucumber.data.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Note> generateRandomNotes(int count, Author author) {
        List<Note> data = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; ++i) {
            Note note = new Note();
            note.setText(generateRandomText(random.nextInt(100) + 11));
            note.setAuthor(author);
        }

        return data;
    }

    private static String generateRandomText(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
