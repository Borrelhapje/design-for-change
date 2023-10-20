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
import java.util.stream.Collectors;

public class SimpleSlideShowFactory extends AbstractSlideShowFactory {

    @Override
    public SlideShow createSlideShow(SlideshowMeta meta, List<Slide> slides, boolean addTitleSlide,
            boolean addTOCSlide) {
        return createSlideShowWithSequences(meta, slides, Collections.emptyList(), addTitleSlide, addTOCSlide);
    }
    
    @Override
    public SlideShow createSlideShowWithSequences(SlideshowMeta meta, List<Slide> slides, List<Sequence> sequences, boolean addTitleSlide, boolean addTOCSlide) {
        List<Slide> allSlides = new ArrayList<>();
        if (addTitleSlide) {
            allSlides.add(new TitleSlide(meta));
        }
        if (addTOCSlide) {
            List<SlideMeta> slideMetaList = slides.stream().map(Slide::getMeta).collect(Collectors.toList());
            allSlides.add(new TocSlide(slideMetaList));
        }
        allSlides.addAll(slides);
        return new SlideShowImpl(allSlides, sequences, meta);
    }

    @Override
    public SlideshowMeta createSlideShowMeta(String title, String subtitle, String presenter, LocalDate presentationDate) {
        return new SimpleSlideshowMeta(title, subtitle, presenter, presentationDate);
    }
}
