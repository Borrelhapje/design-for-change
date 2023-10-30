package nl.ou.desktop;

import nl.ou.domain.Iterator;
import nl.ou.domain.Sequence;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Button;

public class SequenceSelectionPanel extends JPanel {
    private final FrameEntryPoint frameEntryPoint;

    public SequenceSelectionPanel(FrameEntryPoint frameEntryPoint) {
        this.frameEntryPoint = frameEntryPoint;
    }

    void render() {
        DefaultComboBoxModel<Sequence> comboBoxModel = new DefaultComboBoxModel<>();
        Iterator<Sequence> sequences = frameEntryPoint.getSequences();
        while (sequences.hasNext()) {
            sequences.next();
            comboBoxModel.addElement(sequences.current());
        }
        JComboBox<Sequence> sequenceJComboBox = new JComboBox<>(comboBoxModel);
        sequenceJComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getDescription()));
        add(sequenceJComboBox);
        Button startButton = new Button("Start");
        startButton.addActionListener(e -> {
            if (comboBoxModel.getSelectedItem() != null) {
                frameEntryPoint.showSlideShow((Sequence) comboBoxModel.getSelectedItem());
            }
        });
        add(startButton);
        revalidate();
        repaint();
    }
}
