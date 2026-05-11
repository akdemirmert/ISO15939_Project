package data;

import model.Metric;
import model.QualityDimension;
import model.Scenario;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioData {
    public static HashMap<String, ArrayList<Scenario>> getAllScenarios() {
        HashMap<String, ArrayList<Scenario>> data = new HashMap<>();

        ArrayList<Scenario> educationScenarios = new ArrayList<>();
        educationScenarios.add(createEducationAlpha());
        educationScenarios.add(createEducationBeta());
        data.put("Education", educationScenarios);

        ArrayList<Scenario> healthScenarios = new ArrayList<>();
        healthScenarios.add(createHospitalSystem());
        healthScenarios.add(createClinicPortal());
        data.put("Health", healthScenarios);

        return data;
    }

    private static Scenario createEducationAlpha() {
        Scenario scenario = new Scenario("Scenario C - Team Alpha", "Education", "Product Quality");

        QualityDimension usability = new QualityDimension("Usability", 25);
        usability.addMetric(new Metric("SUS score", 50, "Higher", 0, 100, "points", 89));
        usability.addMetric(new Metric("Onboarding time", 50, "Lower", 0, 60, "min", 8));
        scenario.addDimension(usability);

        QualityDimension performance = new QualityDimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Video start time", 50, "Lower", 0, 15, "sec", 4));
        performance.addMetric(new Metric("Concurrent exams", 50, "Higher", 0, 600, "users", 420));
        scenario.addDimension(performance);

        QualityDimension accessibility = new QualityDimension("Accessibility", 20);
        accessibility.addMetric(new Metric("WCAG compliance", 50, "Higher", 0, 100, "%", 76));
        accessibility.addMetric(new Metric("Screen reader score", 50, "Higher", 0, 100, "%", 71));
        scenario.addDimension(accessibility);

        QualityDimension reliability = new QualityDimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, "Higher", 95, 100, "%", 98.8));
        reliability.addMetric(new Metric("MTTR", 50, "Lower", 0, 120, "min", 35));
        scenario.addDimension(reliability);

        QualityDimension functional = new QualityDimension("Functional Suitability", 15);
        functional.addMetric(new Metric("Feature completion", 50, "Higher", 0, 100, "%", 88));
        functional.addMetric(new Metric("Assignment submit rate", 50, "Higher", 0, 100, "%", 93));
        scenario.addDimension(functional);

        return scenario;
    }

    private static Scenario createEducationBeta() {
        Scenario scenario = new Scenario("Scenario D - Team Beta", "Education", "Product Quality");

        QualityDimension usability = new QualityDimension("Usability", 25);
        usability.addMetric(new Metric("SUS score", 50, "Higher", 0, 100, "points", 72));
        usability.addMetric(new Metric("Onboarding time", 50, "Lower", 0, 60, "min", 20));
        scenario.addDimension(usability);

        QualityDimension performance = new QualityDimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Video start time", 50, "Lower", 0, 15, "sec", 7));
        performance.addMetric(new Metric("Concurrent exams", 50, "Higher", 0, 600, "users", 350));
        scenario.addDimension(performance);

        QualityDimension accessibility = new QualityDimension("Accessibility", 20);
        accessibility.addMetric(new Metric("WCAG compliance", 50, "Higher", 0, 100, "%", 62));
        accessibility.addMetric(new Metric("Screen reader score", 50, "Higher", 0, 100, "%", 58));
        scenario.addDimension(accessibility);

        QualityDimension reliability = new QualityDimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, "Higher", 95, 100, "%", 97.4));
        reliability.addMetric(new Metric("MTTR", 50, "Lower", 0, 120, "min", 55));
        scenario.addDimension(reliability);

        QualityDimension functional = new QualityDimension("Functional Suitability", 15);
        functional.addMetric(new Metric("Feature completion", 50, "Higher", 0, 100, "%", 79));
        functional.addMetric(new Metric("Assignment submit rate", 50, "Higher", 0, 100, "%", 84));
        scenario.addDimension(functional);

        return scenario;
    }

    private static Scenario createHospitalSystem() {
        Scenario scenario = new Scenario("Scenario A - Hospital System", "Health", "Product Quality");

        QualityDimension usability = new QualityDimension("Usability", 20);
        usability.addMetric(new Metric("Task completion rate", 50, "Higher", 0, 100, "%", 86));
        usability.addMetric(new Metric("User error rate", 50, "Lower", 0, 30, "%", 7));
        scenario.addDimension(usability);

        QualityDimension performance = new QualityDimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Patient search time", 50, "Lower", 0, 10, "sec", 2.5));
        performance.addMetric(new Metric("Requests per second", 50, "Higher", 0, 500, "req/s", 390));
        scenario.addDimension(performance);

        QualityDimension security = new QualityDimension("Security", 20);
        security.addMetric(new Metric("Security test coverage", 50, "Higher", 0, 100, "%", 81));
        security.addMetric(new Metric("Vulnerability count", 50, "Lower", 0, 20, "count", 4));
        scenario.addDimension(security);

        QualityDimension reliability = new QualityDimension("Reliability", 20);
        reliability.addMetric(new Metric("Availability", 50, "Higher", 95, 100, "%", 99.1));
        reliability.addMetric(new Metric("Incident recovery time", 50, "Lower", 0, 120, "min", 28));
        scenario.addDimension(reliability);

        QualityDimension functional = new QualityDimension("Functional Suitability", 20);
        functional.addMetric(new Metric("Feature completion", 50, "Higher", 0, 100, "%", 91));
        functional.addMetric(new Metric("Correct output tests", 50, "Higher", 0, 100, "%", 87));
        scenario.addDimension(functional);

        return scenario;
    }

    private static Scenario createClinicPortal() {
        Scenario scenario = new Scenario("Scenario B - Clinic Portal", "Health", "Product Quality");

        QualityDimension usability = new QualityDimension("Usability", 20);
        usability.addMetric(new Metric("Appointment success rate", 50, "Higher", 0, 100, "%", 78));
        usability.addMetric(new Metric("Form completion time", 50, "Lower", 0, 20, "min", 9));
        scenario.addDimension(usability);

        QualityDimension performance = new QualityDimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Page response time", 50, "Lower", 0, 8, "sec", 3));
        performance.addMetric(new Metric("Concurrent patients", 50, "Higher", 0, 400, "users", 260));
        scenario.addDimension(performance);

        QualityDimension accessibility = new QualityDimension("Accessibility", 20);
        accessibility.addMetric(new Metric("Mobile accessibility score", 50, "Higher", 0, 100, "%", 69));
        accessibility.addMetric(new Metric("Keyboard navigation score", 50, "Higher", 0, 100, "%", 65));
        scenario.addDimension(accessibility);

        QualityDimension reliability = new QualityDimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, "Higher", 95, 100, "%", 98.0));
        reliability.addMetric(new Metric("MTTR", 50, "Lower", 0, 120, "min", 48));
        scenario.addDimension(reliability);

        QualityDimension security = new QualityDimension("Security", 20);
        security.addMetric(new Metric("Authentication success", 50, "Higher", 0, 100, "%", 88));
        security.addMetric(new Metric("Open vulnerability count", 50, "Lower", 0, 20, "count", 6));
        scenario.addDimension(security);

        return scenario;
    }
}
