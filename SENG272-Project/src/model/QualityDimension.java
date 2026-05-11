package model;

import java.util.ArrayList;

public class QualityDimension {
    private String name;
    private double coefficient;
    private ArrayList<Metric> metrics;

    public QualityDimension(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public ArrayList<Metric> getMetrics() {
        return metrics;
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    public double calculateScore() {
        double total = 0;
        double totalCoefficient = 0;

        for (Metric metric : metrics) {
            total += metric.calculateScore() * metric.getCoefficient();
            totalCoefficient += metric.getCoefficient();
        }

        if (totalCoefficient == 0) {
            return 0;
        }

        return Math.round((total / totalCoefficient) * 10.0) / 10.0;
    }

    public double calculateGap() {
        return Math.round((5.0 - calculateScore()) * 10.0) / 10.0;
    }

    public String getQualityLabel() {
        double score = calculateScore();
        if (score >= 4.5) {
            return "Excellent";
        }
        if (score >= 3.5) {
            return "Good";
        }
        if (score >= 2.5) {
            return "Needs Improvement";
        }
        return "Poor";
    }
}
