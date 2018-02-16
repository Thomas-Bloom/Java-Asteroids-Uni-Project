package game1;

import utilities.JEasyFrame;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<GameObject> objects;
    public static List<GameObject> alive;
    public static Keys ctrl;
    private static int score = 0;
    private static int lives = 3;
    private static Timer timer;
    private static int interval;

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

        countdowninvincibility();

        while(true){
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void update(){
        // Collision between asteroids and bullets
        for(int i = 0; i < objects.size() - 2; i++){
            objects.get(i).collisionHandling(objects.get(objects.size() - 1), 1);

        }
        // Collision between asteroids and player
        for(int i = 0; i < objects.size() - 3; i++){
            if(Ship.invincible){
                // Do nothing, player is invincible
            }
            else
                objects.get(i).collisionHandling(objects.get(objects.size() - 2), 0);
        }


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
        synchronized (Game.class){
            objects.clear();
            objects.addAll(alive);
        }

        int asteroidCount = 0;
        for(GameObject gameObject : alive){
            if(gameObject.getClass().getName().equals("game1.Asteroid")){
                asteroidCount++;
            }
        }
    }

    public static void addScore(int scoreToAdd){
        score += scoreToAdd;
    }

    public static int getScore() {
        return score;
    }

    public static void decrementLives(){
        lives -= 1;
    }

    public static void addLife(){
        lives++;
    }

    public static int getLives() {
        return lives;
    }

    public static void countdowninvincibility(){
        timer = new Timer();
        interval = 2;
        int delay = 1000;
        int period = 1000;
        System.out.println(interval);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(setInterval());
                if(setInterval() >= 0){
                    Ship.invincible = true;

                }
                else
                    Ship.invincible = false;
            }
        }, delay, period);
    }
    private static final int setInterval(){
        if(interval == 0)
            timer.cancel();
        return --interval;
    }
}
