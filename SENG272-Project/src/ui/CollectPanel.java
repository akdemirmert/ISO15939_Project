package ui;

import model.Metric;
import model.QualityDimension;
import model.Scenario;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class CollectPanel extends JPanel {
    private MeasurementApp app;
    private DefaultTableModel tableModel;
    private JTable table;

    public CollectPanel(MeasurementApp app) {
        this.app = app;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("Step 4: Collect Data", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Metric", "Direction", "Range", "Value", "Score", "Coeff / Unit"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> app.previousStep());

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> app.nextStep());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        Scenario scenario = app.getSession().getScenario();

        if (scenario == null) {
            return;
        }

        for (QualityDimension dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                tableModel.addRow(new Object[]{
                        metric.getName(),
                        metric.getDirectionText(),
                        metric.getRangeText(),
                        metric.formatNumber(metric.getRawValue()),
                        String.format("%.1f", metric.calculateScore()),
                        metric.getCoefficient() + " / " + metric.getUnit()
                });
            }
        }
    }
}
