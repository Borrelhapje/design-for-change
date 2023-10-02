package nl.ou.domain;

public interface CompositeContent extends Content{

    Iterator<Content> getIterator();
    
}
