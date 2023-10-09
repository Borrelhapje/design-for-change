package nl.ou;

import nl.ou.domain.SlideShow;
import nl.ou.infra.SlideReaderFactory;
import nl.ou.infra.SlideShowReader;
import nl.ou.infra.SlideShowReaderFormat;

import java.io.IOException;
import java.io.InputStream;

public class JabberPoint {
    public static void main(String[] args) {
        String fileName;
        if (args.length == 0 || args[0].trim().isEmpty()) {
            fileName = "default_slideshow.json";
        } else {
            fileName = args[0].trim();
        }

        SlideShowReader slideReader = SlideReaderFactory.createSlideReader(SlideShowReaderFormat.JSON);
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
            SlideShow slideShow = slideReader.readSlideShow(inputStream);
            System.out.println(slideShow);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
