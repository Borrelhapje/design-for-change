package nl.ou.desktop;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;
import nl.ou.domain.Slide;
import nl.ou.domain.SlideShow;
import nl.ou.domain.StopStrategy;
import nl.ou.services.GUIFacade;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingGUIFacade implements GUIFacade {

    private final SlideShow slideShow;
    private final StopStrategy stopStrategy;
    private JPanel contentPanel;
    private Sequence currentSequence;

    public SwingGUIFacade(SlideShow slideShow, StopStrategy stopStrategy) {
        this.slideShow = slideShow;
        this.stopStrategy = stopStrategy;
        renderFrame();
        showSequenceSelection();
    }

    private void renderFrame() {
        var frame = new JFrame("JabberPoint " + slideShow.getMeta().getTitle());
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
        sequenceSelectionPanel.render();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void showSlideShow(Sequence sequence) {
        this.currentSequence = sequence;
        contentPanel.removeAll();
        SlideShowSequencePanel slideShowSequencePanel = new SlideShowSequencePanel(this);
        contentPanel.add(slideShowSequencePanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public Iterator<Sequence> getSequences() {
        return slideShow.getSequences();
    }

    public Iterator<Slide> getSlideIterator() {
        return currentSequence.getSlides();
    }

    public void stop() {
        stopStrategy.stop(this);
    }
}
