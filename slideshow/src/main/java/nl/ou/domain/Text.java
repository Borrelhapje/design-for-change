package nl.ou.domain;

import java.util.Optional;

public class Text implements Content{
    
    private String text;
    private Content parent;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.empty();
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.doForText(this);
    }

    public void setParent(Content parent) {
        this.parent = parent;
    }

    @Override
    public Content getParent() {
        return parent;
    }
}
