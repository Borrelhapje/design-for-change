package nl.ou.domain;

public interface Sequence {

    String getKey();
        
    String getDescription();

    Iterator<Slide> getSlides();
}
