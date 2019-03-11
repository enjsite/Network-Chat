package swing;

import javax.swing.*;
import java.io.IOException;

public class App {
    private static MainWindow mainWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mainWindow = new MainWindow();
                } catch (IOException | ClassNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
