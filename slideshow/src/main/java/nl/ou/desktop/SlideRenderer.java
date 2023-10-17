package nl.ou.desktop;

import javax.swing.JComponent;
import javax.swing.JPanel;

import nl.ou.domain.Slide;

public class SlideRenderer implements ComponentCreator {

    private final JComponent component;

    SlideRenderer(Slide slide) {
        final var panel = new JPanel();
        this.component = panel;
        final var contentRenderer = new ContentRenderer();
        slide.getContent().accept(contentRenderer);
        panel.add(contentRenderer.getComponent());
        panel.add(new SlideMetaRenderer(slide.getMeta()).getComponent());
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
    
}
