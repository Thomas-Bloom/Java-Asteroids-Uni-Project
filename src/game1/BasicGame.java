package game1;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

public class BasicGame {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<BasicAsteroid> asteroids;

    public BasicGame(){
        asteroids = new ArrayList<>();
        for(int i = 0; i < N_INITIAL_ASTEROIDS; i++){
            asteroids.add(BasicAsteroid.makeRandomAsteroid());
        }
    }

    public static void main(String[] args) throws Exception{
        BasicGame game = new BasicGame();
        BasicView view = new BasicView(game);
        new JEasyFrame(view, "Basic Game");


        while(true){
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void update(){
        for(BasicAsteroid a : asteroids){
            a.update();
        }
    }
}
