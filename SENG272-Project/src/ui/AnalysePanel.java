package ui;

import model.QualityDimension;
import model.Scenario;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class AnalysePanel extends JPanel {
    private MeasurementApp app;
    private JPanel progressPanel;
    private JTextArea gapAnalysisArea;
    private RadarChartPanel radarChartPanel;

    public AnalysePanel(MeasurementApp app) {
        this.app = app;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("Step 5: Analyse", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        progressPanel = new JPanel();
        progressPanel.setLayout(new GridLayout(0, 1, 8, 8));

        JScrollPane progressScrollPane = new JScrollPane(progressPanel);
        progressScrollPane.setBorder(BorderFactory.createTitledBorder("Dimension Scores"));

        radarChartPanel = new RadarChartPanel();
        radarChartPanel.setBorder(BorderFactory.createTitledBorder("Radar Chart"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, progressScrollPane, radarChartPanel);
        splitPane.setResizeWeight(0.45);
        splitPane.setBorder(null);
        add(splitPane, BorderLayout.CENTER);

        gapAnalysisArea = new JTextArea(7, 30);
        gapAnalysisArea.setEditable(false);
        gapAnalysisArea.setBorder(BorderFactory.createTitledBorder("Gap Analysis"));
        add(gapAnalysisArea, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> app.previousStep());

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(backButton);
        add(topButtonPanel, BorderLayout.WEST);
    }

    public void refreshAnalysis() {
        progressPanel.removeAll();
        Scenario scenario = app.getSession().getScenario();
        radarChartPanel.setScenario(scenario);

        if (scenario == null) {
            gapAnalysisArea.setText("No scenario selected.");
            progressPanel.revalidate();
            progressPanel.repaint();
            return;
        }

        QualityDimension weakest = null;

        for (QualityDimension dimension : scenario.getDimensions()) {
            double score = dimension.calculateScore();
            JProgressBar bar = new JProgressBar(0, 50);
            bar.setValue((int) Math.round(score * 10));
            bar.setStringPainted(true);
            bar.setString(dimension.getName() + " - " + String.format("%.1f", score) + "/5 - " + dimension.getQualityLabel());
            progressPanel.add(bar);

            if (weakest == null || score < weakest.calculateScore()) {
                weakest = dimension;
            }
        }

        if (weakest != null) {
            gapAnalysisArea.setText(
                    "Selected Scenario: " + scenario.getName() + "\n" +
                    "Lowest Dimension: " + weakest.getName() + "\n" +
                    "Score: " + String.format("%.1f", weakest.calculateScore()) + "/5\n" +
                    "Gap: " + String.format("%.1f", weakest.calculateGap()) + "\n" +
                    "Quality Level: " + weakest.getQualityLabel() + "\n" +
                    "This dimension has the lowest score and requires the most improvement."
            );
        }

        progressPanel.revalidate();
        progressPanel.repaint();
    }
}
