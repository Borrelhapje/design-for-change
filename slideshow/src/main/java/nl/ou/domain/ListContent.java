package nl.ou.domain;

import java.util.List;
import java.util.Optional;

public class ListContent implements CompositeContent {

    private List<Content> elements;
    private boolean bulleted;

    public Iterator<Content> getIterator() {
        return new ListIterator<>(elements);
    }

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.of(this);
    }
    
}
