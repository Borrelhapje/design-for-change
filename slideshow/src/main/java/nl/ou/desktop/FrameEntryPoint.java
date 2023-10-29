package nl.ou.desktop;

import nl.ou.services.GUIFacade;

import javax.swing.JFrame;

public class FrameEntryPoint {

    private final GUIFacade facade;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        renderFrame();
    }

    private void renderFrame() {
        final var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        final var panel = new SlideShowSequencePanel(facade);
        frame.add(panel);
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
    }
}
