package nl.ou.services.impl;

import java.util.List;

import nl.ou.domain.*;
import nl.ou.domain.impl.NormalSlide;
import nl.ou.domain.impl.SimpleSlideMeta;
import nl.ou.domain.impl.TitleSlide;
import nl.ou.domain.impl.TocSlide;
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

    @Override
    public Slide createTOCSlide(List<SlideMeta> metas) {
        return new TocSlide(metas);
    }

    @Override
    public Slide createTitleSlide(SlideshowMeta meta) {
        return new TitleSlide(meta);
    }
}
