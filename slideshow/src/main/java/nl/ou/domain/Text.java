package nl.ou.domain;

import java.util.Optional;

public class Text implements Content{
    
    private String text;

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.empty();
    }

}
