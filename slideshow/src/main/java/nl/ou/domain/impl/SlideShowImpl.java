package nl.ou.domain.impl;

import java.util.List;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.SlideshowMeta;

public class SlideShowImpl implements SlideShow {

    private List<Slide> slides;
    private List<Sequence> sequences;
    private SlideshowMeta meta;

    public SlideShowImpl(List<Slide> slides, List<Sequence> sequences, SlideshowMeta meta) {
        this.slides = slides;
        this.sequences = sequences;
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

    @Override
    public Iterator<Sequence> getSequences() {
        return new ListIterator<>(sequences);
    }
}
