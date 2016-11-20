package presentation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Button extends JButton {

    private Color hoverBackgroundColor = Color.decode("#FFFFFF");
    private Color pressedBackgroundColor= Color.decode("#FFFFFF");

    public Button() {
        this(null);
    }

    public Button(String text) {
        super(text);
        super.setContentAreaFilled(false);
        //super.setBorderPainted(false);
        super.setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
            super.setBorder(BorderFactory.createEtchedBorder());
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
            super.setForeground(Color.BLACK);
            super.setBorder(BorderFactory.createDashedBorder(Color.black));
        }
        else {
            g.setColor(getBackground());
            this.setForeground(Color.white);
            super.setBorder(new LineBorder(Color.BLACK));
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}
