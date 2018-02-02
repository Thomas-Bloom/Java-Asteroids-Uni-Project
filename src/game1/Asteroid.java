package game1;

import utilities.Vector2D;

import java.awt.*;
import java.util.Random;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class Asteroid extends GameObject {
    public static final double MAX_SPEED = 100;
    public static Vector2D direction = new Vector2D(10, 10);

    public Asteroid(Vector2D pos, Vector2D vel, Color color){
        super(pos, vel, color);
        this.color = color;
        double randomAngle = direction.angle() + Math.PI / 2 * Math.random();
        direction.rotate(randomAngle);
    }

    public static Asteroid makeRandomAsteroid(){
        Random random = new Random();
        double randomXpos = FRAME_WIDTH * (Math.random() * Math.PI);
        double randomYPos = FRAME_HEIGHT * (Math.random() * Math.PI);
        double randomVx = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        double randomVy = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        Asteroid asteroid = new Asteroid(new Vector2D(randomXpos, randomYPos), new Vector2D(randomVx, randomVy), Color.WHITE);
        return asteroid;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval((int)position.x, (int)position.y, 20, 20);
    }
}
