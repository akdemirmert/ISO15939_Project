package ui;

import model.MeasurementSession;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class MeasurementApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private StepIndicatorPanel stepIndicatorPanel;
    private MeasurementSession session;
    private ProfilePanel profilePanel;
    private DefinePanel definePanel;
    private PlanPanel planPanel;
    private CollectPanel collectPanel;
    private AnalysePanel analysePanel;
    private int currentStep;

    public MeasurementApp() {
        session = new MeasurementSession();
        currentStep = 0;

        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        stepIndicatorPanel = new StepIndicatorPanel();
        add(stepIndicatorPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        profilePanel = new ProfilePanel(this);
        definePanel = new DefinePanel(this);
        planPanel = new PlanPanel(this);
        collectPanel = new CollectPanel(this);
        analysePanel = new AnalysePanel(this);

        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(definePanel, "Define");
        cardPanel.add(planPanel, "Plan");
        cardPanel.add(collectPanel, "Collect");
        cardPanel.add(analysePanel, "Analyse");

        add(cardPanel, BorderLayout.CENTER);
        showStep(0);
    }

    public MeasurementSession getSession() {
        return session;
    }

    public void showStep(int step) {
        currentStep = step;
        stepIndicatorPanel.updateStep(step);

        if (step == 0) {
            cardLayout.show(cardPanel, "Profile");
        } else if (step == 1) {
            cardLayout.show(cardPanel, "Define");
        } else if (step == 2) {
            planPanel.refreshTable();
            cardLayout.show(cardPanel, "Plan");
        } else if (step == 3) {
            collectPanel.refreshTable();
            cardLayout.show(cardPanel, "Collect");
        } else if (step == 4) {
            analysePanel.refreshAnalysis();
            cardLayout.show(cardPanel, "Analyse");
        }
    }

    public void nextStep() {
        if (currentStep < 4) {
            showStep(currentStep + 1);
        }
    }

    public void previousStep() {
        if (currentStep > 0) {
            showStep(currentStep - 1);
        }
    }
}
