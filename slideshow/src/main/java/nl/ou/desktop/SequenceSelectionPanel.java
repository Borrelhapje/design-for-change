package nl.ou.desktop;

import javax.swing.JPanel;

public class SequenceSelectionPanel extends JPanel {
    private final FrameEntryPoint frameEntryPoint;

    public SequenceSelectionPanel(FrameEntryPoint frameEntryPoint) {
        this.frameEntryPoint = frameEntryPoint;
        render();
    }

    private void render() {
        frameEntryPoint.showSlideShow(null);
    }
}
