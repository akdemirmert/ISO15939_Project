package ui;

import data.ScenarioData;
import model.MeasurementSession;
import model.Scenario;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;

public class DefinePanel extends JPanel {
    private MeasurementApp app;
    private JRadioButton productQualityButton;
    private JRadioButton processQualityButton;
    private JComboBox<String> modeComboBox;
    private JComboBox<Scenario> scenarioComboBox;
    private HashMap<String, ArrayList<Scenario>> scenarios;

    public DefinePanel(MeasurementApp app) {
        this.app = app;
        this.scenarios = ScenarioData.getAllScenarios();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JLabel title = new JLabel("Step 2: Define Quality Dimensions", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(22f));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        productQualityButton = new JRadioButton("Product Quality", true);
        processQualityButton = new JRadioButton("Process Quality");
        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productQualityButton);
        qualityGroup.add(processQualityButton);

        JPanel qualityPanel = new JPanel();
        qualityPanel.add(productQualityButton);
        qualityPanel.add(processQualityButton);

        modeComboBox = new JComboBox<>(new String[]{"Education", "Health", "Custom"});
        scenarioComboBox = new JComboBox<>();

        modeComboBox.addActionListener(e -> updateScenarioList());

        addRow(formPanel, gbc, 0, "Quality Type", qualityPanel);
        addRow(formPanel, gbc, 1, "Mode", modeComboBox);
        addRow(formPanel, gbc, 2, "Scenario", scenarioComboBox);

        updateScenarioList();
        add(formPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> app.previousStep());

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> saveDefinition());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, java.awt.Component component) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1;
        panel.add(component, gbc);
    }

    private void updateScenarioList() {
        String mode = (String) modeComboBox.getSelectedItem();
        DefaultComboBoxModel<Scenario> model = new DefaultComboBoxModel<>();

        if (mode != null && scenarios.containsKey(mode)) {
            for (Scenario scenario : scenarios.get(mode)) {
                model.addElement(scenario);
            }
        }

        scenarioComboBox.setModel(model);
    }

    private void saveDefinition() {
        String mode = (String) modeComboBox.getSelectedItem();

        if ("Custom".equals(mode)) {
            JOptionPane.showMessageDialog(this, "Custom mode is planned as a future improvement. Please select Education or Health.");
            return;
        }

        Scenario scenario = (Scenario) scenarioComboBox.getSelectedItem();
        if (scenario == null) {
            JOptionPane.showMessageDialog(this, "Please select a scenario to continue.");
            return;
        }

        String qualityType = productQualityButton.isSelected() ? "Product Quality" : "Process Quality";

        MeasurementSession session = app.getSession();
        session.setQualityType(qualityType);
        session.setMode(mode);
        session.setScenario(scenario);
        app.nextStep();
    }
}
