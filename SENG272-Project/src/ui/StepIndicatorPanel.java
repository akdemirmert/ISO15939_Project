package ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class StepIndicatorPanel extends JPanel {
    private String[] steps = {"Profile", "Define", "Plan", "Collect", "Analyse"};
    private JLabel[] labels;

    public StepIndicatorPanel() {
        setLayout(new GridLayout(1, steps.length, 8, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labels = new JLabel[steps.length];

        for (int i = 0; i < steps.length; i++) {
            labels[i] = new JLabel(steps[i], JLabel.CENTER);
            labels[i].setOpaque(true);
            labels[i].setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            add(labels[i]);
        }

        updateStep(0);
    }

    public void updateStep(int activeStep) {
        for (int i = 0; i < labels.length; i++) {
            if (i < activeStep) {
                labels[i].setText("✓ " + steps[i]);
                labels[i].setBackground(new Color(221, 242, 221));
                labels[i].setForeground(new Color(20, 90, 35));
                labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN));
            } else if (i == activeStep) {
                labels[i].setText(steps[i]);
                labels[i].setBackground(new Color(210, 225, 255));
                labels[i].setForeground(new Color(30, 70, 150));
                labels[i].setFont(labels[i].getFont().deriveFont(Font.BOLD));
            } else {
                labels[i].setText(steps[i]);
                labels[i].setBackground(new Color(238, 238, 238));
                labels[i].setForeground(Color.DARK_GRAY);
                labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN));
            }
        }
    }
}
