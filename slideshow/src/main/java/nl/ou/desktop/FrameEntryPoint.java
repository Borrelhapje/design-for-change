package nl.ou.desktop;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.ou.domain.Iterator;
import nl.ou.domain.Slide;
import nl.ou.services.GUIFacade;

public class FrameEntryPoint {

    private final GUIFacade facade;
    private final Iterator<Slide> iterator;
    private final JComponent frame;
    private JButton next;
    private JButton previous;
    private JComponent slideShower;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        this.iterator = facade.getSlideshow().getSlideIterator();
        this.frame = renderFrame();
        onSlideChange();
    }

    private JComponent renderFrame() {
        final var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                facade.getStopStrategy().stop();
            }
        });
        final var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        previous = new JButton(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.previous();
                onSlideChange();
            }
        });
        previous.setText("Back");
        next = new JButton(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.next();
                onSlideChange();
            }
        });
        next.setText("Next");
        final var buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(previous);
        buttonPanel.add(next);
        panel.add(buttonPanel);
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        return panel;
    }
    
    private void onSlideChange() {
        if (slideShower != null) {
            frame.remove(slideShower);
            frame.invalidate();
        }
        previous.setEnabled(iterator.hasPrevious());
        next.setEnabled(iterator.hasNext());
        slideShower = new SlideRenderer(iterator.current()).getComponent();
        frame.add(slideShower);
        frame.revalidate();
        frame.repaint();
    }
    
}
