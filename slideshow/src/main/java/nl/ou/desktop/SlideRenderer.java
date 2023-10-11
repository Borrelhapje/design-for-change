package nl.ou.desktop;

import javax.swing.JComponent;

import nl.ou.domain.Slide;

public class SlideRenderer implements ComponentCreator {

    private final JComponent component;

    SlideRenderer(Slide slide) {

    }

    @Override
    public JComponent getComponent() {
        return component;
    }
    
}
