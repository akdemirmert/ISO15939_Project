package ui;

import model.QualityDimension;
import model.Scenario;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class RadarChartPanel extends JPanel {
    private Scenario scenario;

    public RadarChartPanel() {
        setPreferredSize(new Dimension(360, 360));
        setBackground(Color.WHITE);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2 + 10;
        int radius = Math.min(width, height) / 2 - 70;

        g2.setColor(Color.BLACK);
        g2.drawString("Radar Chart", 15, 25);

        if (scenario == null || scenario.getDimensions().isEmpty()) {
            g2.drawString("No scenario selected", centerX - 55, centerY);
            return;
        }

        ArrayList<QualityDimension> dimensions = scenario.getDimensions();
        int count = dimensions.size();

        drawGrid(g2, centerX, centerY, radius, count);
        drawScores(g2, centerX, centerY, radius, dimensions);
        drawLabels(g2, centerX, centerY, radius, dimensions);
    }

    private void drawGrid(Graphics2D g2, int centerX, int centerY, int radius, int count) {
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(new Color(210, 210, 210));

        for (int level = 1; level <= 5; level++) {
            Polygon polygon = new Polygon();
            double levelRadius = radius * (level / 5.0);

            for (int i = 0; i < count; i++) {
                double angle = -Math.PI / 2 + (2 * Math.PI * i / count);
                int x = centerX + (int) Math.round(Math.cos(angle) * levelRadius);
                int y = centerY + (int) Math.round(Math.sin(angle) * levelRadius);
                polygon.addPoint(x, y);
            }

            g2.drawPolygon(polygon);
        }

        for (int i = 0; i < count; i++) {
            double angle = -Math.PI / 2 + (2 * Math.PI * i / count);
            int x = centerX + (int) Math.round(Math.cos(angle) * radius);
            int y = centerY + (int) Math.round(Math.sin(angle) * radius);
            g2.drawLine(centerX, centerY, x, y);
        }
    }

    private void drawScores(Graphics2D g2, int centerX, int centerY, int radius, ArrayList<QualityDimension> dimensions) {
        Polygon scorePolygon = new Polygon();
        int count = dimensions.size();

        for (int i = 0; i < count; i++) {
            double score = dimensions.get(i).calculateScore();
            double scoreRadius = radius * (score / 5.0);
            double angle = -Math.PI / 2 + (2 * Math.PI * i / count);
            int x = centerX + (int) Math.round(Math.cos(angle) * scoreRadius);
            int y = centerY + (int) Math.round(Math.sin(angle) * scoreRadius);
            scorePolygon.addPoint(x, y);
        }

        g2.setColor(new Color(70, 130, 180, 90));
        g2.fillPolygon(scorePolygon);
        g2.setColor(new Color(40, 90, 140));
        g2.setStroke(new BasicStroke(2f));
        g2.drawPolygon(scorePolygon);

        for (int i = 0; i < scorePolygon.npoints; i++) {
            int x = scorePolygon.xpoints[i];
            int y = scorePolygon.ypoints[i];
            g2.fillOval(x - 4, y - 4, 8, 8);
        }
    }

    private void drawLabels(Graphics2D g2, int centerX, int centerY, int radius, ArrayList<QualityDimension> dimensions) {
        g2.setColor(Color.BLACK);
        FontMetrics metrics = g2.getFontMetrics();
        int count = dimensions.size();

        for (int i = 0; i < count; i++) {
            QualityDimension dimension = dimensions.get(i);
            double angle = -Math.PI / 2 + (2 * Math.PI * i / count);
            int x = centerX + (int) Math.round(Math.cos(angle) * (radius + 45));
            int y = centerY + (int) Math.round(Math.sin(angle) * (radius + 45));
            String label = shortenName(dimension.getName()) + " " + String.format("%.1f", dimension.calculateScore());
            int textWidth = metrics.stringWidth(label);
            g2.drawString(label, x - textWidth / 2, y);
        }
    }

    private String shortenName(String name) {
        if (name.length() <= 14) {
            return name;
        }
        return name.substring(0, 14) + ".";
    }
}
