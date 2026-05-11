package model;

import java.util.ArrayList;

public class Scenario {
    private String name;
    private String mode;
    private String qualityType;
    private ArrayList<QualityDimension> dimensions;

    public Scenario(String name, String mode, String qualityType) {
        this.name = name;
        this.mode = mode;
        this.qualityType = qualityType;
        this.dimensions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public String getQualityType() {
        return qualityType;
    }

    public ArrayList<QualityDimension> getDimensions() {
        return dimensions;
    }

    public void addDimension(QualityDimension dimension) {
        dimensions.add(dimension);
    }

    @Override
    public String toString() {
        return name;
    }
}
