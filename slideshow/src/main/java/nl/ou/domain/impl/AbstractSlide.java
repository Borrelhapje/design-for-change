package nl.ou.domain.impl;

import nl.ou.domain.Slide;
import nl.ou.domain.SlideMeta;

public abstract class AbstractSlide implements Slide {

    private SlideMeta meta;

    @Override
    public SlideMeta getMeta() {
        return meta;
    }
    
}
