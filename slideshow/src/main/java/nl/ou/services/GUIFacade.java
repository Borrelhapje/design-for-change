package nl.ou.services;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;

public interface GUIFacade {

    Iterator<Sequence> getSequences();

    void showSequenceSelection();

    void showSlideShow(Sequence sequence);

    Iterator<Slide> getSlideIterator();

    void stop();
}
