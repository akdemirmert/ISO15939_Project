import ui.MeasurementApp;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MeasurementApp().setVisible(true);
            }
        });
    }
}
