package nl.ou.services;

import java.util.List;

import nl.ou.domain.Content;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideMeta;
import nl.ou.domain.SlideshowMeta;
import nl.ou.services.impl.SimpleSlideFactory;

public abstract class AbstractSlideFactory {
    private static AbstractSlideFactory SLIDE_FACTORY;

    public abstract SlideMeta createSlideMeta(String title, int number);

    public abstract Slide createSlide(SlideMeta meta, Content content);

    public abstract Slide createTOCSlide(List<SlideMeta> metas);

    public abstract Slide createTitleSlide(SlideshowMeta meta);

    public static AbstractSlideFactory getSlideFactory() {
        if (SLIDE_FACTORY == null) {
            SLIDE_FACTORY = new SimpleSlideFactory();
        }
        return SLIDE_FACTORY;
    }
}
