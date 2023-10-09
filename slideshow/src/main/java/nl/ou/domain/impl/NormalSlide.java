package nl.ou.domain.impl;

import nl.ou.domain.Content;
import nl.ou.domain.SlideMeta;

public class NormalSlide extends AbstractSlide {
    private Content content;

    public NormalSlide(Content content, SlideMeta meta) {
        super(meta);
        this.content = content;
    }

    @Override
    public Content getContent() {
        return content;
    }
}
