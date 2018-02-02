package game1;

import utilities.JEasyFrame;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class BasicGame {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public List<GameObject> objects;
    public static BasicKeys ctrl;

    public BasicGame(){
        objects = new ArrayList<>();

        for(int i = 0; i < N_INITIAL_ASTEROIDS; i++){
            objects.add(Asteroid.makeRandomAsteroid());
        }
        ctrl = new BasicKeys();
        objects.add(new Ship(ctrl));
    }

    public static void main(String[] args) throws Exception{
        BasicGame game = new BasicGame();
        BasicView view = new BasicView(game);
        new JEasyFrame(view, "Basic Game").addKeyListener(ctrl);

        while(true){
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void update(){
        for(GameObject gameObject : objects){
            gameObject.update();
        }
    }
}
