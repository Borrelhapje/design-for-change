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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleSlideShowFactory extends AbstractSlideShowFactory {

    @Override
    public SlideShow createSlideShow(SlideshowMeta meta, List<Slide> slides) {
        return createSlideShow(meta, slides, Collections.emptyList());
    }
    
    @Override
    public SlideShow createSlideShow(SlideshowMeta meta, List<Slide> slides, List<Sequence> sequences) {
        List<Slide> allSlides = new ArrayList<>();
        allSlides.addAll(slides);
        return new SlideShowImpl(allSlides, sequences, meta);
    }

    @Override
    public SlideshowMeta createSlideShowMeta(String title, String subtitle, String presenter,
            LocalDate presentationDate) {
        return new SimpleSlideshowMeta(title, subtitle, presenter, presentationDate);
    }
    
    @Override
    public Slide createTOCSlide(List<SlideMeta> metas) {
        return new TocSlide(metas);
    }

    @Override
    public Slide createTitleSlide(SlideshowMeta meta) {
        return new TitleSlide(meta);
    }
}
