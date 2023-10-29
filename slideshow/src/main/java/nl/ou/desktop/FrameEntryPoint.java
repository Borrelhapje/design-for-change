package nl.ou.desktop;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.ou.services.GUIFacade;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameEntryPoint {

    private final GUIFacade facade;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        renderFrame();
    }

    private void renderFrame() {
        final var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                facade.getStopStrategy().stop();
            }
        });
        final var panel = new SlideShowSequencePanel(facade);
        frame.add(panel);
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
    }
}
