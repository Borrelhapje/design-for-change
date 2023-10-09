package nl.ou.infra;

import nl.ou.infra.impl.JsonSlideShowReader;
import nl.ou.services.*;

public class SlideReaderFactory {
    public static SlideShowReader createSlideReader(SlideShowReaderFormat format) {
        AbstractSlideShowFactory slideShowFactory = AbstractSlideShowFactory.getSlideShowFactory();
        AbstractSlideFactory slideFactory = AbstractSlideFactory.getSlideFactory();
        AbstractContentFactory contentFactory = AbstractContentFactory.getContentFactory();

        switch (format) {
            case JSON:
                return new JsonSlideShowReader(slideShowFactory, slideFactory, contentFactory);
            default:
                throw new IllegalArgumentException("Unsupported format for reading slide shows: ");
        }
    }
}
