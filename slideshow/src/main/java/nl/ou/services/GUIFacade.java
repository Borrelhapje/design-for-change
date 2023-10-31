package nl.ou.services;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;

public interface GUIFacade {

    Iterator<Sequence> getSequences();

    void showSequenceSelection();

    void showSlideShow(Sequence sequence);

    void stop();
}
