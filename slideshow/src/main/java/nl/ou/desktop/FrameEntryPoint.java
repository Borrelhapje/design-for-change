package nl.ou.desktop;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;
import nl.ou.services.GUIFacade;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameEntryPoint {

    private final GUIFacade facade;
    private JPanel contentPanel;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        renderFrame();
        showSequenceSelection();
    }

    private void renderFrame() {
        var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        contentPanel = new JPanel();
        frame.add(contentPanel);
    }

    public void showSequenceSelection() {
        contentPanel.removeAll();
        SequenceSelectionPanel sequenceSelectionPanel = new SequenceSelectionPanel(this);
        contentPanel.add(sequenceSelectionPanel);
    }

    public void showSlideShow(Sequence sequence) {
        contentPanel.removeAll();
        SlideShowSequencePanel slideShowSequencePanel = new SlideShowSequencePanel(this);
        contentPanel.add(slideShowSequencePanel);
    }

    public Iterator<Slide> getSlideIterator() {
        return facade.getSlideshow().getSlideIterator();
    }

    public void stop() {
        facade.getStopStrategy().stop();
    }
}
