package model;

public class Metric {
    private String name;
    private double coefficient;
    private String direction;
    private double minValue;
    private double maxValue;
    private String unit;
    private double rawValue;

    public Metric(String name, double coefficient, String direction, double minValue, double maxValue, String unit, double rawValue) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.rawValue = rawValue;
    }

    public String getName() {
        return name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getDirection() {
        return direction;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public String getUnit() {
        return unit;
    }

    public double getRawValue() {
        return rawValue;
    }

    public void setRawValue(double rawValue) {
        this.rawValue = rawValue;
    }

    public boolean isHigherBetter() {
        return direction.equalsIgnoreCase("Higher");
    }

    public String getDirectionText() {
        if (isHigherBetter()) {
            return "Higher is better";
        }
        return "Lower is better";
    }

    public String getRangeText() {
        return formatNumber(minValue) + "-" + formatNumber(maxValue);
    }

    public double calculateScore() {
        double score;
        if (maxValue == minValue) {
            score = 1.0;
        } else if (isHigherBetter()) {
            score = 1 + ((rawValue - minValue) / (maxValue - minValue)) * 4;
        } else {
            score = 5 - ((rawValue - minValue) / (maxValue - minValue)) * 4;
        }

        if (score < 1) {
            score = 1;
        }
        if (score > 5) {
            score = 5;
        }

        return Math.round(score * 2.0) / 2.0;
    }

    public String formatNumber(double value) {
        if (value == Math.floor(value)) {
            return String.valueOf((int) value);
        }
        return String.format("%.1f", value);
    }
}
