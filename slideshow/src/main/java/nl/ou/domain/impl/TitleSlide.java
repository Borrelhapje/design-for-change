package nl.ou.domain.impl;

import nl.ou.domain.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TitleSlide extends AbstractSlide {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private final SlideshowMeta showMeta;

    public TitleSlide(SlideshowMeta showMeta) {
        super(null);
        this.showMeta = showMeta;
    }

    @Override
    public Content getContent() {
        Text title = new Text(showMeta.getTitle());
        Text subtitle = new Text(showMeta.getSubtitle());
        Text presenter = new Text(showMeta.getPresenter());
        Text date = showMeta.getDate() != null ? new Text(showMeta.getDate().format(dateTimeFormatter)) : null;

        return createListContent(
                title,
                createListContent(
                        subtitle,
                        createListContent(
                                presenter,
                                date
                        )
                )
        );
    }

    private ListContent createListContent(Content... elements) {
        return new ListContent(List.of(elements), false);
    }
}
