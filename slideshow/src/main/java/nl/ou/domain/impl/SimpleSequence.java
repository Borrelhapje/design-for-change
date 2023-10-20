package nl.ou.domain.impl;

import java.util.List;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;

public class SimpleSequence implements Sequence {

    private String description;
    private String key;
    private List<Slide> slides;

    public SimpleSequence(String description, String key, List<Slide> slides) {
        this.description = description;
        this.key = key;
        this.slides = slides;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Iterator<Slide> getSlides() {
        return new ListIterator<>(slides);
    }
    
}
