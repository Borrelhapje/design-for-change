package nl.ou.domain;

import java.util.List;

public class SlideShow {

    private List<Slide> slides;
    private SlideshowMeta meta;

    public Iterator<Slide> getSlideIterator() {
        return new ListIterator<>(slides);
    }

    public SlideshowMeta getMeta() {
        return meta;
    }
    
}
