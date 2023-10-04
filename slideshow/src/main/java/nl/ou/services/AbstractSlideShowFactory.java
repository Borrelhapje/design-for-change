package nl.ou.services;

import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.SlideshowMeta;
import nl.ou.services.impl.SimpleSlideShowFactory;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractSlideShowFactory {
    private static AbstractSlideShowFactory SLIDE_SHOW_FACTORY;

    public abstract SlideShow createSlideShow(SlideshowMeta meta, List<Slide> slides, boolean addTitleSlide, boolean addTOCSlide);

    public abstract SlideshowMeta createSlideShowMeta(String title, String subtitle, String presenter, LocalDate presentationDate);

    public static AbstractSlideShowFactory getSlideShowFactory() {
        if (SLIDE_SHOW_FACTORY == null) {
            SLIDE_SHOW_FACTORY = new SimpleSlideShowFactory();
        }
        return SLIDE_SHOW_FACTORY;
    }
}
