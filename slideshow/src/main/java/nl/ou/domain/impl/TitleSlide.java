package nl.ou.domain.impl;

import nl.ou.domain.Content;
import nl.ou.domain.SlideMeta;
import nl.ou.domain.SlideshowMeta;

public class TitleSlide extends AbstractSlide {

    private SlideshowMeta showMeta;

    public TitleSlide(SlideshowMeta showMeta) {
        super(null);
        this.showMeta = showMeta;
    }

    @Override
    public Content getContent() {
        //genereer uit showMeta?
        return null;
    }
}
