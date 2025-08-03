import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BallHoleGameAdvanced extends JFrame implements ActionListener {
    JTextField inputField;
    JButton moveButton, resetButton;
    int ballX = 60, ballY = 110; // starting position
    int targetX = 60;
    Timer timer;

    public BallHoleGameAdvanced() {
        setTitle("🎯 Ball Hole Game - Advanced");
        setSize(550, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel label = new JLabel("🎯 Enter hole (1-6):");
        label.setBounds(30, 20, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        add(label);

        inputField = new JTextField();
        inputField.setBounds(150, 20, 40, 30);
        add(inputField);

        moveButton = new JButton("Move Ball");
        moveButton.setBounds(210, 20, 100, 30);
        moveButton.addActionListener(this);
        add(moveButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(320, 20, 80, 30);
        resetButton.addActionListener(e -> resetBall());
        add(resetButton);

        // Timer for smooth movement
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ballX < targetX) ballX++;
                else if (ballX > targetX) ballX--;
                else timer.stop(); // Stop when ball reaches
                repaint();
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        int x = 50, y = 100;

        // Draw 6 holes
        for (int i = 1; i <= 6; i++) {
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 50, 50);
            g.setColor(new Color(180, 220, 250));
            g.fillOval(x, y, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("Hole " + i, x + 8, y + 70);
            x += 70;
        }

        // Draw ball
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 30, 30);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int hole = Integer.parseInt(inputField.getText());
            if (hole >= 1 && hole <= 6) {
                Toolkit.getDefaultToolkit().beep(); // sound
                targetX = 60 + (hole - 1) * 70; // Update target
                timer.start(); // Start animation
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 6.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void resetBall() {
        ballX = 60;
        targetX = 60;
        repaint();
    }

    public static void main(String[] args) {
        new BallHoleGameAdvanced();
    }
}
