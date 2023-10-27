package nl.ou.domain.impl;

import java.util.List;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.SlideshowMeta;

public class SlideShowImpl implements SlideShow {

    private List<Sequence> sequences;
    private SlideshowMeta meta;

    public SlideShowImpl(List<Sequence> sequences, SlideshowMeta meta) {
        this.sequences = sequences;
        this.meta = meta;
    }

    @Override
    public SlideshowMeta getMeta() {
        return meta;
    }

    @Override
    public Iterator<Slide> getSlideIterator() {
        if (sequences.isEmpty()) {
            return null;
        }
        return sequences.get(0).getSlides();
    }

    @Override
    public Iterator<Sequence> getSequences() {
        return new ListIterator<>(sequences);
    }
}
