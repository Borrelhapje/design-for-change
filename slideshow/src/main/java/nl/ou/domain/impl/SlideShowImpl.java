package nl.ou.domain.impl;

import java.util.List;

import nl.ou.domain.Iterator;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.SlideshowMeta;

public class SlideShowImpl implements SlideShow {

    private List<Slide> slides;
    private SlideshowMeta meta;

    public SlideShowImpl(List<Slide> slides, SlideshowMeta meta) {
        this.slides = slides;
        this.meta = meta;
    }

    @Override
    public SlideshowMeta getMeta() {
        return meta;
    }

    @Override
    public Iterator<Slide> getSlideIterator() {
        return new ListIterator<>(slides);
    }
}
