package nl.ou.domain.impl;

import nl.ou.domain.SlideMeta;

public class SimpleSlideMeta implements SlideMeta {
    private String title;
    private int number;

    public SimpleSlideMeta(String title, int number) {
        this.title = title;
        this.number = number;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int number() {
        return number;
    }
}
