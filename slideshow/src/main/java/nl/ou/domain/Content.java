package nl.ou.domain;

import java.util.Optional;

public interface Content {

    Optional<CompositeContent> getComposite();

    void accept(ContentVisitor visitor);

    void setParent(Content parent);

    Content getParent();
}
