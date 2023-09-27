package nl.ou.domain;

public interface Iterator<T>{

    T current();

    boolean next();

    boolean previous();

    boolean hasNext();

    boolean hasPrevious();
    
}
