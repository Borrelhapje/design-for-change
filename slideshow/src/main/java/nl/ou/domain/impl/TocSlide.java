package nl.ou.domain.impl;

import nl.ou.domain.Content;
import nl.ou.domain.SlideMeta;

import java.util.List;

public class TocSlide extends AbstractSlide {

    private final List<SlideMeta> slideMetaList;

    public TocSlide(List<SlideMeta> slideMetaList) {
        super(null);
        this.slideMetaList = slideMetaList;
    }

    @Override
    public Content getContent() {
        // genereer? of zetten we dat in een aparte class?
        return null;
    }
}
