package nl.ou.desktop;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;

import nl.ou.domain.SlideMeta;

public class SlideMetaRenderer implements ComponentCreator {

    private final JComponent component;

    SlideMetaRenderer(SlideMeta meta) {
        component = new JMenu();
        component.add(new JLabel(Integer.toString(meta.number())));
    }

    public JComponent getComponent() {
        return component;
    }
    
}
