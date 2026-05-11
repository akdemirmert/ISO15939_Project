# SENG272-Project

## Project Name
ISO 15939 Measurement Process Simulator

## Description
This project is a Java Swing desktop application developed for SENG272 Software Project II. It simulates the simplified ISO/IEC 15939 software measurement process with a five-step wizard structure.

The application allows the user to enter profile information, define the measurement scope, select a scenario, review planned metrics, see collected raw data, calculate scores, and analyze the weakest quality dimension.

## Technologies
- Java SE 17+
- Java Swing
- CardLayout
- ArrayList
- HashMap
- OOP structure with separated model, data, and UI classes

## Application Steps
1. Profile
2. Define
3. Plan
4. Collect
5. Analyse

## Features
- Five-step wizard interface
- Step indicator with active and completed step states
- Profile validation with user-friendly warning messages
- Product Quality / Process Quality selection
- Education and Health modes
- Two scenarios for each implemented mode
- Scenario-based quality dimensions and metrics
- Read-only planning table
- Raw data and calculated score table
- Score calculation between 1.0 and 5.0
- Rounding to the nearest 0.5
- Dimension-based weighted averages
- Gap analysis for the weakest dimension
- Java2D radar chart for dimension score visualization
- Quality labels: Excellent, Good, Needs Improvement, Poor

## Project Structure
```text
SENG272-Project/
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── Metric.java
│   │   ├── QualityDimension.java
│   │   ├── Scenario.java
│   │   └── MeasurementSession.java
│   ├── data/
│   │   └── ScenarioData.java
│   └── ui/
│       ├── MeasurementApp.java
│       ├── StepIndicatorPanel.java
│       ├── ProfilePanel.java
│       ├── DefinePanel.java
│       ├── PlanPanel.java
│       ├── CollectPanel.java
│       ├── AnalysePanel.java
│       └── RadarChartPanel.java
├── .gitignore
└── README.md
```

## Compile and Run

```bash
javac -d out src/Main.java src/model/*.java src/data/*.java src/ui/*.java
java -cp out Main
```

## Data Source
Scenario data is hard-coded inside the `ScenarioData` class. The data is organized with a `HashMap<String, ArrayList<Scenario>>`, where each mode contains multiple scenarios.

## Score Calculation
For higher-is-better metrics:

```text
score = 1 + (value - min) / (max - min) * 4
```

For lower-is-better metrics:

```text
score = 5 - (value - min) / (max - min) * 4
```

The result is clamped between 1.0 and 5.0 and rounded to the nearest 0.5.

## Improvement Points
- Custom mode is shown as a future improvement but is not fully implemented.
- Scenario data is hard-coded; file-based or database-based data loading could be added.
- The user interface can be improved with a more modern visual design.
- Raw metric values are currently predefined; editable input fields could be added in the Collect step.
