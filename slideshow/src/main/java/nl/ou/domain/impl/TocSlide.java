package nl.ou.domain.impl;

import nl.ou.domain.Content;
import nl.ou.domain.ListContent;
import nl.ou.domain.SlideMeta;
import nl.ou.domain.Text;

import java.util.List;
import java.util.stream.Collectors;

public class TocSlide extends AbstractSlide {

    private final List<SlideMeta> slideMetaList;

    public TocSlide(List<SlideMeta> slideMetaList) {
        super(null);
        this.slideMetaList = slideMetaList;
    }

    @Override
    public Content getContent() {
        return new ListContent(
                slideMetaList.stream()
                        .map(SlideMeta::title)
                        .map(Text::new)
                        .collect(Collectors.toList())
                , true);
    }
}
