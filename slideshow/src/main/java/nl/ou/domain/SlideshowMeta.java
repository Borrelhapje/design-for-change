package nl.ou.domain;

import java.time.LocalDate;

public interface SlideshowMeta {
    
    String getTitle();

    String getSubtitle();

    String getPresenter();

    LocalDate getDate();

}
