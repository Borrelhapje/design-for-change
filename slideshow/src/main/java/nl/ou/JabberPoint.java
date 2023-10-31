package nl.ou;

import nl.ou.domain.SlideShow;
import nl.ou.infra.SlideReaderFactory;
import nl.ou.infra.SlideShowReader;
import nl.ou.infra.SlideShowReaderFormat;
import nl.ou.services.AbstractGUIFacadeFactory;

import java.io.IOException;
import java.io.InputStream;

public class JabberPoint {
    public static void main(String[] args) {
        String fileName;
        if (args.length == 0 || args[0].trim().isEmpty()) {
            fileName = "all_elements_slideshow.json";
        } else {
            fileName = args[0].trim();
        }

        SlideShowReader slideReader = SlideReaderFactory.createSlideReader(SlideShowReaderFormat.JSON);
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
            SlideShow slideShow = slideReader.readSlideShow(inputStream);
            System.out.println(slideShow);
            AbstractGUIFacadeFactory.getFactory().create(slideShow).showSequenceSelection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
