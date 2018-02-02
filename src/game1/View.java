package game1;

import javax.swing.*;
import java.awt.*;

public class View extends JComponent {
    // background colour
    public static final Color BG_COLOR = Color.black;

    private Game game;

    public View(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        for(GameObject gameObject : game.objects){
            gameObject.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}