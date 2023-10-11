package nl.ou.desktop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import nl.ou.domain.Iterator;
import nl.ou.domain.Slide;
import nl.ou.services.GUIFacade;

public class FrameEntryPoint {

    private final GUIFacade facade;
    private final Iterator<Slide> iterator;
    private final JFrame frame;
    private JComponent slideShower;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        this.iterator = facade.getSlideshow().getSlideIterator();
        this.frame = renderFrame();
    }

    private JFrame renderFrame() {
        final var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                facade.getStopStrategy().stop();
            }
        });
        final var nextButton = new JButton(new AbstractAction() {
            public boolean isEnabled() {
                return iterator.hasNext();
            }

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.next();
                onSlideChange();
            }
        });
        nextButton.setText("Next");
        frame.add(nextButton);
        final var prevButton = new JButton(new AbstractAction() {
            public boolean isEnabled() {
                return iterator.hasPrevious();
            }

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.previous();
                onSlideChange();
            }
        });
        prevButton.setText("Back");
        frame.add(prevButton);
        return frame;
    }
    
    private void onSlideChange() {
        if (slideShower != null) {
            frame.remove(slideShower);
        }
        frame.add(new SlideRenderer(iterator.current()).getComponent());
    }
    
}
