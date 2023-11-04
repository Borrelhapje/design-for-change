package nl.ou.services.impl;

import nl.ou.domain.SlideMeta;
import nl.ou.domain.impl.SimpleSlideshowMeta;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.SlideshowMeta;
import nl.ou.domain.impl.SlideShowImpl;
import nl.ou.domain.impl.TitleSlide;
import nl.ou.domain.impl.TocSlide;
import nl.ou.services.AbstractSlideShowFactory;

import java.time.LocalDate;
import java.util.List;

public class SimpleSlideShowFactory extends AbstractSlideShowFactory {
    
    @Override
    public SlideShow createSlideShow(SlideshowMeta meta, List<Slide> slides, List<Sequence> sequences) {
        return new SlideShowImpl(sequences, meta);
    }

    @Override
    public SlideshowMeta createSlideShowMeta(String title, String subtitle, String presenter,
            LocalDate presentationDate) {
        return new SimpleSlideshowMeta(title, subtitle, presenter, presentationDate);
    }
    

}
