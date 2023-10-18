package nl.ou.domain;

import java.net.URI;
import java.util.Optional;

public class Figure implements Content{

    private URI source;
    private Content parent;

    public Figure(URI source) {
        this.source = source;
    }

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.empty();
    }

    public URI getSource() {
        return source;
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.doForFigure(this);
    }

    public void setParent(Content parent) {
        this.parent = parent;
    }

    @Override
    public Content getParent() {
        return parent;
    }
}
