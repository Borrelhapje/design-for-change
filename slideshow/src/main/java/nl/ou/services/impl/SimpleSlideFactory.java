package nl.ou.services.impl;

import nl.ou.domain.*;
import nl.ou.domain.impl.NormalSlide;
import nl.ou.domain.impl.SimpleSlideMeta;
import nl.ou.services.AbstractSlideFactory;

public class SimpleSlideFactory extends AbstractSlideFactory {
    @Override
    public SlideMeta createSlideMeta(String title, int number) {
        return new SimpleSlideMeta(title, number);
    }

    @Override
    public Slide createSlide(SlideMeta meta, Content content) {
        return new NormalSlide(content, meta);
    }
}
