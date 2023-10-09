package nl.ou.domain.impl;

import nl.ou.domain.SlideshowMeta;

import java.time.LocalDate;

public class SimpleSlideshowMeta implements SlideshowMeta {
    private String title;
    private String subtitle;
    private String presenter;
    private LocalDate date;

    public SimpleSlideshowMeta(String title, String subtitle, String presenter, LocalDate date) {
        this.title = title;
        this.subtitle = subtitle;
        this.presenter = presenter;
        this.date = date;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public String getPresenter() {
        return presenter;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }
}
