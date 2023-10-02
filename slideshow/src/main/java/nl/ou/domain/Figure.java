package nl.ou.domain;

import java.net.URI;
import java.util.Optional;

public class Figure implements Content{

    private URI source;

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.empty();
    }
    
}
