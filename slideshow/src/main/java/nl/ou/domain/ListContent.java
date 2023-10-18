package nl.ou.domain;

import java.util.List;
import java.util.Optional;

import nl.ou.domain.impl.ListIterator;

public class ListContent implements CompositeContent {

    private List<Content> elements;
    private boolean bulleted;
    private Content parent;

    public ListContent(List<Content> elements, boolean bulleted) {
        this.elements = elements;
        this.bulleted = bulleted;
    }

    public Iterator<Content> getIterator() {
        return new ListIterator<>(elements);
    }

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.of(this);
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.doForListStart(this);
        elements.forEach(c -> c.accept(visitor));
        visitor.doForListEnd(this);
    }

    public void setParent(Content parent) {
        this.parent = parent;
    }

    @Override
    public Content getParent() {
        return parent;
    }

    public int getLevel() {
        int level = 0;
        Content predecessor = parent;
        while (predecessor != null && predecessor instanceof ListContent) {
            level++;
            predecessor = predecessor.getParent();
        }
        return level;
    }
}
