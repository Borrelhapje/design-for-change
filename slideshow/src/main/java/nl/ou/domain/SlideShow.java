package nl.ou.domain;

public interface SlideShow {

    Iterator<Slide> getSlideIterator();

    SlideshowMeta getMeta();

    Iterator<Sequence> getSequences();
}
