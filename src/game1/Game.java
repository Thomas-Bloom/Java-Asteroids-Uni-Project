package game1;

import utilities.JEasyFrame;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<GameObject> objects;
    public static List<GameObject> alive;
    public static Keys ctrl;

    public Game(){
        objects = new ArrayList<>();

        for(int i = 0; i < N_INITIAL_ASTEROIDS; i++){
            objects.add(Asteroid.makeRandomAsteroid());
        }
        ctrl = new Keys();
        objects.add(new Ship(ctrl));
    }

    public static void main(String[] args) throws Exception{
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "Basic Game").addKeyListener(ctrl);

        while(true){
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void update(){
        alive = new ArrayList<>();
        for(GameObject gameObject : objects) {
            gameObject.update();
            // NOT DEAD
            if(!gameObject.dead)
                alive.add(gameObject);
        }

        if(Ship.bullet != null){
            alive.add(Ship.bullet);
            Ship.bullet = null;
        }
        objects.clear();

        objects.addAll(alive);
    }
}
