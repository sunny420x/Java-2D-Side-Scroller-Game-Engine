package Projects.Java_2D_Engine;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Run extends JPanel {
    public static ShapeDrawing shapeDrawing = new ShapeDrawing();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField textField = new JTextField();
            Keychecker keyChecker = new Keychecker(shapeDrawing);
            textField.addKeyListener(keyChecker);

            JFrame frame = new JFrame("Java 2D Engine");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.add(textField);
            frame.getContentPane().add(shapeDrawing);
            frame.setVisible(true);

            // Start the game loop using a Swing Timer
            int fps = 60;
            Timer timer = new Timer(1000 / fps, e -> {
                shapeDrawing.update();
                keyChecker.updateMovement();
                shapeDrawing.repaint();
            });
            timer.start();
        });
    }
}

class Keychecker extends KeyAdapter {
    private static final int speed = 5;
    private ShapeDrawing shapeDrawing;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public Keychecker(ShapeDrawing shapeDrawing) {
        this.shapeDrawing = shapeDrawing;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char ch = event.getKeyChar();
        if (ch == 'a') moveLeft = true;
        if (ch == 'd') moveRight = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        char ch = event.getKeyChar();
        if (ch == 'a') moveLeft = false;
        if (ch == 'd') moveRight = false;
    }

    public void updateMovement() {
        if (moveLeft) {
            if(ShapeDrawing.posX < 600) {
                ShapeDrawing.posX += speed;
            }
        }
        if (moveRight) {
            if(ShapeDrawing.absolute_playerPosX < (ShapeDrawing.MapObj[ShapeDrawing.MapObj.length - 1][0]) + 27) {
                ShapeDrawing.posX -= speed;
            }
            // ShapeDrawing.posX -= speed;
        }
    }
}
